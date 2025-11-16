package com.songguesser.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.songguesser.backend.dto.UserDto;
import com.songguesser.backend.dto.UserRankingDto;
import com.songguesser.backend.service.UserService.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sync")
    public ResponseEntity<Void> syncUser(@RequestBody UserDto dto) {
        userService.syncUser(dto);
        return ResponseEntity.ok().build();
    }
    
    
    @GetMapping("/ranking")
    public ResponseEntity<List<UserRankingDto>> ranking() {
        return ResponseEntity.ok(userService.getRanking());
    }
    
}
