package com.songguesser.backend.service.SongService;

import com.songguesser.backend.dto.SongDto;
import com.songguesser.backend.model.entity.Song;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SongService {
    List<Song> getAll();
    void delete(Long id);
    Song save(Song entity);
    List<Song> find(Map<String, Object> filter);
    Song getBy(Map<String, Object> filter);
    Optional<Song> getById(Long id);
    Boolean existById(Long id);
    Optional<SongDto> getRandomSong();
    Optional<SongDto> getRandomSong(java.util.List<Long> excludedIds, String languageFilter);
    Optional<SongDto> getRandomSong(String languageFilter);
}
