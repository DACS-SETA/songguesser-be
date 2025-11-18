package com.songguesser.backend.model.repository;

import com.songguesser.backend.model.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

	@Query(value = "SELECT * FROM song s WHERE (:languageFilter IS NULL OR :languageFilter = 'mixed' OR s.language = :languageFilter) ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
	Optional<Song> getRandomSong(@org.springframework.data.repository.query.Param("languageFilter") String languageFilter);

	@Query(value = "SELECT * FROM song s WHERE (id NOT IN (:excludedIds)) AND (:languageFilter IS NULL OR :languageFilter = 'mixed' OR s.language = :languageFilter) ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
	Optional<Song> getRandomSongNotIn(@org.springframework.data.repository.query.Param("excludedIds") List<Long> excludedIds, @org.springframework.data.repository.query.Param("languageFilter") String languageFilter);

}
