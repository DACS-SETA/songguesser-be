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
    
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private com.songguesser.backend.conector.ItunesConnector itunesConnector;
    
    @Autowired
    private com.songguesser.backend.model.repository.SongRepository songRepository;

    @Override
    public GameStartResponseDto startNewGame(String keycloakId) {
        Optional<User> userOpt = userRepository.findByKeycloakId(keycloakId);
        if (userOpt.isEmpty()) {
            log.warn("No existe usuario con keycloakId={} — no se puede crear partida.", keycloakId);
            return null;
        }

        User user = userOpt.get();

        Game game = new Game();
        game.setUser(user);
        gameRepository.save(game);

        log.info("Nueva partida creada con ID {} para usuario {}", game.getId(), user.getUsername());

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

    // If the game is already finished, do not process guesses
    if (game.isFinished()) return null;

        List<Round> existingRounds = roundRepository.findByGame(game);
        List<Long> usedSongIds = existingRounds.stream()
                .map(r -> r.getSong().getId())
                .filter(Objects::nonNull)
                .toList();

    SongDto randomSong = songService.getRandomSong(usedSongIds).orElse(null);

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
    public Optional<GameSummaryDto> surrender(Long gameId) {
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            game.setFinished(true);
            game.setEndDate(LocalDateTime.now());
            gameRepository.save(game);
            
            
            User user = game.getUser();
            if (user != null) {
                int prevScore = user.getTotalScore() != null ? user.getTotalScore() : 0;
                int gameScore = game.getScoreTotal() != null ? game.getScoreTotal() : 0;
                user.setTotalScore(prevScore + gameScore);
                user.setGamesPlayed((user.getGamesPlayed() == null ? 0 : user.getGamesPlayed()) + 1);
                userRepository.save(user);
            }
            
            log.info("Partida {} marcada como terminada (rendición)", gameId);
            return getSummary(gameId);
        }
        return Optional.empty();
    }

    @Override
    public Optional<GameSummaryDto> finish(Long gameId) {
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            game.setFinished(true);
            game.setEndDate(LocalDateTime.now());
            
            User user = game.getUser();
            if (user != null) {
                int prevScore = user.getTotalScore() != null ? user.getTotalScore() : 0;
                int gameScore = game.getScoreTotal() != null ? game.getScoreTotal() : 0;
                user.setTotalScore(prevScore + gameScore);
                user.setGamesPlayed((user.getGamesPlayed() == null ? 0 : user.getGamesPlayed()) + 1);
                userRepository.save(user);
            }
            
            gameRepository.save(game);
            
            log.info("Partida {} finalizada exitosamente", gameId);
            return getSummary(gameId);
        }
        return Optional.empty();
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

    @Override
    public RoundResponseDto submitGuess(Long gameId, GuessDto guessDto) {
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        if (optionalGame.isEmpty()) return null;

        Game game = optionalGame.get();

        List<Round> rounds = roundRepository.findByGame(game);
        if (rounds.isEmpty()) return null;

        // Get the latest round (by createdAt)
        Round currentRound = rounds.stream()
                .max(Comparator.comparing(Round::getCreatedAt))
                .orElse(null);

        if (currentRound == null) return null;

        Long songInternalId = currentRound.getSong() != null ? currentRound.getSong().getId() : null;
        if (songInternalId == null) return null;

        // Fetch song entity to get external itunes id
        Optional<com.songguesser.backend.model.entity.Song> songEntityOpt = songService.getById(songInternalId);
        if (songEntityOpt.isEmpty()) return null;

        com.songguesser.backend.model.entity.Song songEntity = songEntityOpt.get();

        // Retrieve full song DTO from the Itunes connector so we have previewUrl/trackName, etc.
        com.songguesser.backend.dto.SongDto songDto = null;
        if (songEntity.getItunesSongId() != null) {
            try {
                songDto = itunesConnector.getById(songEntity.getItunesSongId());
            } catch (Exception e) {
                log.warn("No se pudo obtener datos de iTunes para song itunesId={}", songEntity.getItunesSongId());
            }
        }

        // Fallback: build a minimal SongDto from stored entity
        if (songDto == null) {
            songDto = new com.songguesser.backend.dto.SongDto();
            songDto.setInternalId(songEntity.getId());
            songDto.setTrackName(songEntity.getTrackName());
            songDto.setArtistName(songEntity.getArtistName());
        }

        String correctAnswer = songDto.getTrackName() != null ? songDto.getTrackName() : "";
        String userGuess = guessDto.getGuess() != null ? guessDto.getGuess() : "";

        RoundResponseDto response = new RoundResponseDto();
        response.setRoundId(currentRound.getId());
        response.setSong(songDto);

        // Comparison using trim() + equalsIgnoreCase()
        if (userGuess.trim().equalsIgnoreCase(correctAnswer.trim())) {
            // Correct
            currentRound.setCorrect(true);
            Integer time = guessDto.getTime() != null ? guessDto.getTime() : 30;
            if (time > 30) time = 30;
            if (time < 0) time = 0;

            int baseScore = 100;
            int maxBonus = 900;
            
            // Fórmula de decaimiento lineal
            int score = baseScore + (int) (maxBonus * ( (30.0 - time) / 30.0 ));
            currentRound.setScoreObtained(score);
            response.setIsCorrect(true);
            response.setScore(score);
            // Update total game score
            game.setScoreTotal((game.getScoreTotal() == null ? 0 : game.getScoreTotal()) + score);
        } else {
            // Incorrect
            currentRound.setCorrect(false);
            currentRound.setScoreObtained(0);
            response.setIsCorrect(false);
            response.setScore(0);
        }

        // Persist changes
        roundRepository.save(currentRound);
        gameRepository.save(game);

        // finished flag (optional) - keep as false for now
        response.setFinished(game.isFinished());

        return response;
    }
}
