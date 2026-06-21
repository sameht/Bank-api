package com.kobita.banking.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(
    @NotBlank(message="Username is required")
    String username,

    @NotBlank(message = "password is required")
    String password
) {
}
