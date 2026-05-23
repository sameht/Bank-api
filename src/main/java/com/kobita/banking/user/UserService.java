package com.kobita.banking.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kobita.banking.user.dto.AddUserDto;
import com.kobita.banking.user.dto.UserDto;
import com.kobita.banking.user.dto.UserResponseDto;

@Service
public interface UserService {
    public List<UserResponseDto> findAll();
    public UserResponseDto findById(Integer id);
    public UserResponseDto createUser(AddUserDto dto);
    public UserResponseDto updateUser(UserDto dto);
    public void deleteUser(Integer id);
}
