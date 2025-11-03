package com.songguesser.backend.service.GameService;

import com.songguesser.backend.dto.*;
import java.util.Optional;

public interface GameService {

    GameStartResponseDto startNewGame();
    RoundResponseDto addRound(Long gameId);
    void surrender(Long gameId);
    Optional<GameSummaryDto> getSummary(Long gameId);
}
