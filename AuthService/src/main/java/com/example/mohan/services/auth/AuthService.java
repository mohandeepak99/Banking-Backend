package com.example.mohan.services.auth;

import com.example.mohan.dto.SignupRequest;
import com.example.mohan.dto.UserDto;

public interface AuthService {
    UserDto signupUser(SignupRequest signupRequest);
    boolean hasUserWithEmail(String email);
}
