package com.kobita.banking.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kobita.banking.exception.ApiException;
import com.kobita.banking.user.dto.AddUserDto;
import com.kobita.banking.user.dto.UserDto;
import com.kobita.banking.user.dto.UserResponseDto;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserResponseDto> findAll(){
        return userRepository.findAll()
            .stream()
            .map(user -> userMapper.toUserResponseDto(user))
            .toList();
    }

    
    public UserResponseDto createUser(AddUserDto dto) {
        var userExists = userRepository.existsByEmail(dto.email());
        if(userExists){
            throw new ApiException("Email already exists", HttpStatus.CONFLICT);
        }
        var user = userMapper.toUser(dto);
        userRepository.save(user);
        return userMapper.toUserResponseDto(user);
    }

    @Override
    public UserResponseDto findById(Integer id) {
        var user= userRepository.findById(id)
            .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));
        return userMapper.toUserResponseDto(user);


    }

    @Override
    public UserResponseDto updateUser(UserDto dto) {
        User user = userRepository.findById(dto.id())
            .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));

        if(userRepository.existsByEmail(dto.email()) != null){
            new ApiException("Email already exists", HttpStatus.CONFLICT);
        }
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setRole(dto.role());
        
        userRepository.save(user);
        return userMapper.toUserResponseDto(user);
    }

    @Override
    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new ApiException("User not found", HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
    }

}
