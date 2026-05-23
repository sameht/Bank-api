package com.kobita.banking.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserDto(
    @NotBlank(message= "user is required")
    String username,

    @NotBlank(message = "Email is required")
    @Email(message= "Invalid Email format")
    String email,

    String role
) {

}
