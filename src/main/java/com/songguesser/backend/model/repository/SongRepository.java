package com.songguesser.backend.model.repository;

import com.songguesser.backend.model.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

	@Query(value = "SELECT * FROM song ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
	Optional<Song> getRandomSong();

	@Query(value = "SELECT * FROM song WHERE id NOT IN (:excludedIds) ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
	Optional<Song> getRandomSongNotIn(@org.springframework.data.repository.query.Param("excludedIds") List<Long> excludedIds);

}
