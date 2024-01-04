package com.Tech.PasswordManager.model.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MyPasswordDTO(
        long id,
        long userId,
        @NotBlank(message = "Name cannot be blank")
        @Size(min = 2, message = "Name must contain at least 5 characters")
        String nameService,
        @NotBlank(message = "Password cannot be blank")
        String password,
        String login,
        String observation) {
}
