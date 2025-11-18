package com.songguesser.backend.controller;

import com.songguesser.backend.dto.*;
import com.songguesser.backend.service.GameService.GameService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;
 
    @PostMapping("/start")
    public ResponseEntity<GameStartResponseDto> startGame(@RequestParam String keycloakId, @RequestParam(required = false) String language) {
        log.info("→ Iniciando nueva partida para usuario {} con idioma {}", keycloakId, language);
        GameStartResponseDto dto = gameService.startNewGame(keycloakId, language);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.badRequest().build();
    }


    @PostMapping("/{gameId}/round")
    public ResponseEntity<RoundResponseDto> addRound(@PathVariable Long gameId, @RequestBody(required = false) GuessDto guess, @RequestParam(required = false) String language) {
        // If a guess is provided in the body, process it. Otherwise, add a new round.
        if (guess != null && guess.getGuess() != null && !guess.getGuess().isBlank()) {
            log.info("→ Procesando respuesta para partida {}: {}", gameId, guess.getGuess());
            RoundResponseDto dto = gameService.submitGuess(gameId, guess);
            return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
        } else {
            log.info("→ Agregando ronda a partida {} con idioma {}", gameId, language);
            RoundResponseDto dto = gameService.addRound(gameId, language);
            return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{gameId}/surrender")
    public ResponseEntity<GameSummaryDto> surrender(@PathVariable Long gameId) {
        log.info("→ Partida {} marcada como rendida", gameId);
        return gameService.surrender(gameId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{gameId}/finish")
    public ResponseEntity<GameSummaryDto> finish(@PathVariable Long gameId) {
        log.info("→ Finalizando partida {}", gameId);
        return gameService.finish(gameId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{gameId}/summary")
    public ResponseEntity<GameSummaryDto> getSummary(@PathVariable Long gameId) {
        log.info("→ Resumen de la partida {}", gameId);
        return gameService.getSummary(gameId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
