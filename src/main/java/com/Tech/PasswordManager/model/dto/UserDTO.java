package com.Tech.PasswordManager.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDTO(
        long id,
        @NotBlank(message = "login cannot be blank")
        @Email(message = "login is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
        String login,
        @NotBlank(message = "Name cannot be blank")
        @Size(max = 255, message = "Name must be at most 255 characters")
        String name,
        String password
) {}
