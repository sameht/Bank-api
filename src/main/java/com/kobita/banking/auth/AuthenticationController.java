package com.kobita.banking.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kobita.banking.auth.dto.AuthenticationRequest;
import com.kobita.banking.auth.dto.AuthenticationResponse;
import com.kobita.banking.auth.dto.RegisterRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest dto){
        return ResponseEntity.ok(authenticationService.register(dto));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest dto){
        return ResponseEntity.ok(authenticationService.authenticate(dto));
    }
}
