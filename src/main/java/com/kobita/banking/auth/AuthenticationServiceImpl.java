package com.kobita.banking.auth;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kobita.banking.auth.dto.AuthenticationRequest;
import com.kobita.banking.auth.dto.AuthenticationResponse;
import com.kobita.banking.auth.dto.RegisterRequest;
import com.kobita.banking.config.JwtService;
import com.kobita.banking.exception.ApiException;
import com.kobita.banking.role.Role;
import com.kobita.banking.role.RoleRepository;
import com.kobita.banking.user.User;
import com.kobita.banking.user.UserRepository;
@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final PasswordEncoder passwordEncoder;
    final JwtService jwtService;
    final AuthenticationManager authenticationManager;
    
    public AuthenticationServiceImpl(UserRepository userRepository, 
        RoleRepository roleRepository, 
        PasswordEncoder passwordEncoder,
        JwtService jwtService,
        AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationResponse register(RegisterRequest dto) {
        if(userRepository.findByEmail(dto.email()).isPresent()){
            throw new ApiException("Email already exists", HttpStatus.CONFLICT);
        }
        var user = new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));

        Role role = roleRepository.findById(dto.roleId())
            .orElseThrow(() -> new ApiException("", HttpStatus.NOT_FOUND));

        user.setRole(role);

        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest dto) {
        try {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(dto.email(), dto.password())
        );
        } catch (Exception e) {
            e.printStackTrace(); 
            throw e;
        }

        User user = userRepository.findByEmail(dto.email()).orElseThrow(() -> new ApiException("Email not registred", HttpStatus.NOT_FOUND));
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

}
