package com.songguesser.backend.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RoundDto {
    private Long id;
    private Long gameId;
    private Long songId;
    private boolean isCorrect;
    private Integer scoreObtained;
    private LocalDateTime createdAt;
}
