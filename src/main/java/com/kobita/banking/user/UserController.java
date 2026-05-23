package com.kobita.banking.user;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kobita.banking.user.dto.AddUserDto;
import com.kobita.banking.user.dto.UpdateUserDto;
import com.kobita.banking.user.dto.UserDto;
import com.kobita.banking.user.dto.UserResponseDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDto> findAll(){
        return userService.findAll();
    }
    
    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable Integer id){
        return userService.findById(id);
    }

    @PostMapping
    public UserResponseDto createUser(@Valid @RequestBody AddUserDto dto){
        return userService.createUser(dto);
    }

    @PutMapping("/{id}")
    public UserResponseDto updateUser(@Valid @RequestBody UpdateUserDto dto, @PathVariable Integer id){
        return userService.updateUser(dto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
    }

}
