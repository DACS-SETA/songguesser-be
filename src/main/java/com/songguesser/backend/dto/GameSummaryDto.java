package com.songguesser.backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class GameSummaryDto {
    private Long gameId;
    private int totalRounds;
    private int correctRounds;
    private int totalScore;
    private List<RoundSummaryDto> rounds;
}
