package com.example.mohan.dto;

import com.example.mohan.enums.UserRole;

import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String name;
    private String email;
    private String password;
    private UserRole userRole;
}
