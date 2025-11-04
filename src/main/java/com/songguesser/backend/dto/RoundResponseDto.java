package com.songguesser.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoundResponseDto {
    private Long roundId;
    private SongDto song;
    // Resultado de la comparación
    private Boolean isCorrect;
    private Integer score;
    private Boolean finished;
}
