package com.songguesser.backend.service.UserService;


import com.songguesser.backend.dto.UserDto;
import com.songguesser.backend.dto.UserRankingDto;
import com.songguesser.backend.model.entity.User;
import com.songguesser.backend.model.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
                return dto;
            })
            .toList();
    }

}
