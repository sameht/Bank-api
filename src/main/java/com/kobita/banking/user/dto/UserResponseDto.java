package com.kobita.banking.user.dto;

public record UserResponseDto(
    Integer Id,
    String username,
    String email,
    String role
) {

}
