package com.songguesser.backend.dto;

import lombok.Data;

@Data
public class RoundResponseDto {
    private Long roundId;
    private SongDto song;
}
