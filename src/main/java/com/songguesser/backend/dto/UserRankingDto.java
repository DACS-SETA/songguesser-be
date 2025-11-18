package com.songguesser.backend.dto;

import lombok.Data;

@Data
public class UserRankingDto {
    private Long userId;
    private String username;
    private String email;
    private Integer totalScore;
    private Integer gamesPlayed;
}
