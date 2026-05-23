package com.kobita.banking.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDto(

    @NotBlank(message= "Id is required")
    Integer id,

    @NotBlank(message= "Username is required")
    String username,
    
    @NotBlank(message = "Email is required")
    @Email(message= "Invalid Email format")
    String email,
    
    String role
) {

}
