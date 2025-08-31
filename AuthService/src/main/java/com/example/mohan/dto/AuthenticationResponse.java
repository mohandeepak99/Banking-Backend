package com.example.mohan.dto;

import com.example.mohan.enums.UserRole;

import lombok.Data;
@Data
public class AuthenticationResponse {
    private String jwt;
    private Long userId;
    private UserRole userRole;
}
