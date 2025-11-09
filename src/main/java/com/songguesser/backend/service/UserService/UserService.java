package com.songguesser.backend.service.UserService;


import com.songguesser.backend.dto.UserDto;
import com.songguesser.backend.model.entity.User;
import com.songguesser.backend.model.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void syncUser(UserDto dto) {
        if (!userRepository.existsByKeycloakId(dto.getKeycloakId())) {
            User user = new User();
            user.setKeycloakId(dto.getKeycloakId());
            user.setUsername(dto.getUsername());
            user.setEmail(dto.getEmail());
            userRepository.save(user);
        }
    }
}
