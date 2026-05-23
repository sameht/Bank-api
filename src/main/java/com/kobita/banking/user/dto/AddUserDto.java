package com.kobita.banking.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddUserDto(

    @NotBlank(message= "user is required")
    String username,

    @NotBlank(message = "Email is required")
    @Email(message= "Invalid Email format")
    String email,

    @NotBlank(message = "password is required")
    @Size(min= 6, message = "Password must be at least 6 characters")
    String password,

    Integer roleId
) {

}
