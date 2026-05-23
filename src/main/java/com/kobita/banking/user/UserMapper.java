package com.kobita.banking.user;
import org.springframework.stereotype.Service;

import com.kobita.banking.role.Role;
import com.kobita.banking.user.dto.AddUserDto;
import com.kobita.banking.user.dto.UserDto;
import com.kobita.banking.user.dto.UserResponseDto;

@Service
public class UserMapper {

    public UserResponseDto toUserResponseDto(User user){
        return new UserResponseDto(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getRole()
        );
    }

    public User toUser(AddUserDto dto){
        var user = new User();
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setEmail(dto.email());
        user.setRole(new Role(dto.roleId()));
        return user;
    }
}