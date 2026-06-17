package com.kobita.banking.auth;

import org.springframework.stereotype.Service;

import com.kobita.banking.auth.dto.AuthenticationRequest;
import com.kobita.banking.auth.dto.AuthenticationResponse;
import com.kobita.banking.auth.dto.RegisterRequest;
@Service
public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest dto);
    AuthenticationResponse authenticate(AuthenticationRequest dto);

}
