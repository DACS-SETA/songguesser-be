package com.songguesser.backend.service.GameService;

import com.songguesser.backend.dto.*;
import java.util.Optional;

public interface GameService {

    GameStartResponseDto startNewGame(String keycloakId, String languageFilter);
    RoundResponseDto addRound(Long gameId, String languageFilter);
    RoundResponseDto submitGuess(Long gameId, GuessDto guessDto);
    Optional<GameSummaryDto> surrender(Long gameId);
    Optional<GameSummaryDto> getSummary(Long gameId);
    Optional<GameSummaryDto> finish(Long gameId);
}
