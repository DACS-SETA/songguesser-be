package com.songguesser.backend.service.GameService;

import com.songguesser.backend.dto.*;
import java.util.Optional;

public interface GameService {

    GameStartResponseDto startNewGame();
    RoundResponseDto addRound(Long gameId);
    RoundResponseDto submitGuess(Long gameId, GuessDto guessDto);
    Optional<GameSummaryDto> surrender(Long gameId);
    Optional<GameSummaryDto> getSummary(Long gameId);
}
