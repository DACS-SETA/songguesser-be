package com.songguesser.backend.conector;

import com.songguesser.backend.dto.SongDto;
import lombok.Data;
import java.util.List;

@Data
public class ItunesResponse {
    private int resultCount;
    private List<SongDto> results;
}
