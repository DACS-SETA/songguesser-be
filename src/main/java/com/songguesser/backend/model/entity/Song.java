package com.songguesser.backend.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "song")
@Getter
@Setter
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "artist_name")
    private String artistName;

    @Column(name = "track_name")
    private String trackName;

    @Column(name = "itunes_song_id")
    private Long itunesSongId;
}
