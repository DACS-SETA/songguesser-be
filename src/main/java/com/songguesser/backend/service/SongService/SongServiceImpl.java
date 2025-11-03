package com.songguesser.backend.service.SongService;

import com.songguesser.backend.conector.ItunesConnector;
import com.songguesser.backend.dto.SongDto;
import com.songguesser.backend.model.entity.Song;
import com.songguesser.backend.model.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ItunesConnector itunesConnector;

    @Override
    public List<Song> getAll() {
        return songRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public Song save(Song entity) {
        return songRepository.save(entity);
    }

    @Override
    public List<Song> find(Map<String, Object> filter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Song getBy(Map<String, Object> filter) {
        throw new UnsupportedOperationException();
    }


    @Override
    public Optional<SongDto> getRandomSong() {
        Optional<Song> randomSong = songRepository.getRandomSong();
        if (randomSong.isEmpty()) return Optional.empty();

        Song songEntity = randomSong.get();

        SongDto songDto = itunesConnector.getById(songEntity.getItunesSongId());
        if (songDto == null) return Optional.empty();

        songDto.setInternalId(songEntity.getId());
        songDto.setItunesId(songEntity.getItunesSongId());

        return Optional.of(songDto);
    }

    @Override
    public Optional<Song> getById(Long id) {
        return songRepository.findById(id);
    }

    @Override
    public Boolean existById(Long id) {
        return songRepository.existsById(id);
    }
}
