package com.songguesser.backend.dto;

import lombok.Data;

@Data
public class UserDto {
    private String keycloakId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
}
