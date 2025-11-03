package com.songguesser.backend.conector;

import com.songguesser.backend.dto.SongDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "itunesConnector", url = "${CONECTOR_URL:http://localhost:9002/conector/itunes}")

public interface ItunesConnector {


    @GetMapping("/id/{id}")
    SongDto getById(@PathVariable("id") Long id);

    @GetMapping("/random")
    SongDto getRandomSong();

  
    @GetMapping("/random/{genre}")
    SongDto getRandomByGenre(@PathVariable("genre") String genre);
}
