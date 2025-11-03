package com.songguesser.backend.dto;

import lombok.Data;

@Data
public class RoundSummaryDto {
    private String songName;
    private boolean isCorrect;
    private int scoreObtained;
}
