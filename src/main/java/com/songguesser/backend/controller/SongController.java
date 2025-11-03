package com.songguesser.backend.controller;

import com.songguesser.backend.dto.SongDto;
import com.songguesser.backend.model.entity.Song;
import com.songguesser.backend.service.SongService.SongService;
import com.songguesser.backend.exeptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping("/random")
    public ResponseEntity<SongDto> getRandomSong() throws ResourceNotFoundException {
        Optional<SongDto> song = songService.getRandomSong();

        if (song.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró ninguna canción.");
        }

        return new ResponseEntity<>(song.get(), HttpStatus.OK);
    }

}
