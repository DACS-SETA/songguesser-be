package com.songguesser.backend.dto;

import lombok.Data;

@Data
public class UserProfileDto {
    private String username;
    private String email;
    private Integer totalScore;
    private Long gamesPlayed;
    private String avatarUrl;
}