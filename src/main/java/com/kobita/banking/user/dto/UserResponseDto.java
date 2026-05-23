package com.kobita.banking.user.dto;

import com.kobita.banking.role.Role;

public record UserResponseDto(
    Integer Id,
    String username,
    String email,
    Role role
) {

}
