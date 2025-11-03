package com.songguesser.backend.service.GameService;

import com.songguesser.backend.dto.*;
import com.songguesser.backend.model.entity.*;
import com.songguesser.backend.model.repository.*;
import com.songguesser.backend.service.SongService.SongService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.songguesser.backend.service.GameService.GameService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private SongService songService;

    @Override
    public GameStartResponseDto startNewGame() {
        Game game = new Game();
        gameRepository.save(game);

        log.info("Nueva partida creada con ID {}", game.getId());

        SongDto randomSong = songService.getRandomSong().orElse(null);

        if (randomSong == null) {
            log.warn("No se pudo obtener una canción inicial para la partida {}", game.getId());
            return null;
        }

        Round round = new Round();
        round.setGame(game);

        Song song = new Song();
        song.setId(randomSong.getInternalId());
        round.setSong(song);
        roundRepository.save(round);

        GameStartResponseDto dto = new GameStartResponseDto();
        dto.setGameId(game.getId());
        dto.setRoundId(round.getId());
        dto.setSong(randomSong);
        return dto;
    }

    @Override
    public RoundResponseDto addRound(Long gameId) {
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        if (optionalGame.isEmpty()) return null;

        Game game = optionalGame.get();

        List<Round> existingRounds = roundRepository.findByGame(game);
        List<Long> usedSongIds = existingRounds.stream()
                .map(r -> r.getSong().getId())
                .filter(Objects::nonNull)
                .toList();

        SongDto randomSong = songService.getRandomSong()
                .filter(s -> !usedSongIds.contains(s.getInternalId()))
                .orElse(null);

        if (randomSong == null) return null;

        Round round = new Round();
        round.setGame(game);
        Song song = new Song();
        song.setId(randomSong.getInternalId());
        round.setSong(song);
        roundRepository.save(round);

        RoundResponseDto dto = new RoundResponseDto();
        dto.setRoundId(round.getId());
        dto.setSong(randomSong);
        return dto;
    }

    @Override
    public void surrender(Long gameId) {
        gameRepository.findById(gameId).ifPresent(game -> {
            game.setFinished(true);
            game.setEndDate(LocalDateTime.now());
            gameRepository.save(game);
            log.info("Partida {} marcada como terminada (rendición)", gameId);
        });
    }

    @Override
    public Optional<GameSummaryDto> getSummary(Long gameId) {
        return gameRepository.findById(gameId).map(game -> {
            List<Round> rounds = roundRepository.findByGame(game);
            int totalScore = rounds.stream().mapToInt(Round::getScoreObtained).sum();
            int correctRounds = (int) rounds.stream().filter(Round::isCorrect).count();

            GameSummaryDto summary = new GameSummaryDto();
            summary.setGameId(game.getId());
            summary.setTotalRounds(rounds.size());
            summary.setCorrectRounds(correctRounds);
            summary.setTotalScore(totalScore);
            summary.setRounds(rounds.stream().map(r -> {
                RoundSummaryDto dto = new RoundSummaryDto();
                dto.setSongName(r.getSong().getTrackName());
                dto.setCorrect(r.isCorrect());
                dto.setScoreObtained(r.getScoreObtained());
                return dto;
            }).collect(Collectors.toList()));
            return summary;
        });
    }
}
