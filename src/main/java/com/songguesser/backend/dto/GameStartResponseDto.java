package com.songguesser.backend.dto;

import lombok.Data;

@Data
public class GameStartResponseDto {
    private Long gameId;
    private Long roundId;
    private SongDto song;
}
