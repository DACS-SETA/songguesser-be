package com.songguesser.backend.service.UserService;


import com.songguesser.backend.dto.UpdateProfileDto;
import com.songguesser.backend.dto.UserDto;
import com.songguesser.backend.dto.UserProfileDto;
import com.songguesser.backend.dto.UserRankingDto;
import com.songguesser.backend.model.entity.User;
import com.songguesser.backend.model.repository.GameRepository;
import com.songguesser.backend.model.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    public void syncUser(UserDto dto) {

        User user = userRepository.findByKeycloakId(dto.getKeycloakId())
            .orElseGet(User::new);

        user.setKeycloakId(dto.getKeycloakId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());

        userRepository.save(user);
    }

    
    public List<UserRankingDto> getRanking() {
        return userRepository.findAllByOrderByTotalScoreDesc()
            .stream()
            .map(u -> {
                UserRankingDto dto = new UserRankingDto();
                dto.setUserId(u.getId());
                dto.setUsername(u.getUsername());
                dto.setEmail(u.getEmail());
                dto.setTotalScore(u.getTotalScore());
                dto.setGamesPlayed(u.getGamesPlayed() != null ? u.getGamesPlayed() : 0);
                return dto;
            })
            .toList();
    }

    public UserProfileDto getUserProfile(String keycloakId) {
        User user = userRepository.findByKeycloakId(keycloakId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        UserProfileDto dto = new UserProfileDto();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setTotalScore(user.getTotalScore());
        dto.setGamesPlayed(user.getGamesPlayed() != null ? user.getGamesPlayed().longValue() : 0L);
        dto.setAvatarUrl(user.getAvatarUrl());
        return dto;
    }

    public void updateProfile(String keycloakId, UpdateProfileDto dto) {
        User user = userRepository.findByKeycloakId(keycloakId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        if (dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
        }
        if (dto.getAvatarUrl() != null) {
            user.setAvatarUrl(dto.getAvatarUrl());
        }
        userRepository.save(user);
    }

}
