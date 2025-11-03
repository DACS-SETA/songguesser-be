package com.songguesser.backend.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class GameDto {
    private Long id;
    private boolean isFinished;
    private Integer scoreTotal;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
