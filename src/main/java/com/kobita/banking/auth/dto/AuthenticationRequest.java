package com.kobita.banking.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(
    @NotBlank(message="Email is required")
    @Email(message= "Email format is invalid")
    String email,

    @NotBlank(message = "password is required")
    String password
) {
}
