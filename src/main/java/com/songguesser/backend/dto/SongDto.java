package com.songguesser.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongDto {

    private Long internalId;

    private Long itunesId;

    private String artistName;
    private String trackName;
    private String collectionName;
    private String primaryGenreName;
    private String releaseDate;
    private String artworkUrl100;
    private String previewUrl;
    private String language;
}

