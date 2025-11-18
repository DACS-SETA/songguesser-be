package com.songguesser.backend.dto;

import lombok.Data;

@Data
public class UpdateProfileDto {
    private String username;
    private String avatarUrl;
}