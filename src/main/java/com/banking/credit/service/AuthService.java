package com.banking.credit.service;

import org.springframework.stereotype.Service;
import com.banking.credit.dao.UserRepository;
import com.banking.credit.dto.LoginRequest;
import com.banking.credit.dto.LoginResponse;
import com.banking.credit.entity.User;
import com.banking.credit.exception.InvalidCredentialsException;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository
                .findByEmailAndIsActiveTrue(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return new LoginResponse(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}
