package com.songguesser.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.songguesser.backend.dto.UpdateProfileDto;
import com.songguesser.backend.dto.UserDto;
import com.songguesser.backend.dto.UserProfileDto;
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

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> getUserProfile(@RequestParam String keycloakId) {
        UserProfileDto profile = userService.getUserProfile(keycloakId);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profile")
    public ResponseEntity<Void> updateUserProfile(@RequestParam String keycloakId, @RequestBody UpdateProfileDto dto) {
        userService.updateProfile(keycloakId, dto);
        return ResponseEntity.ok().build();
    }
    
}
