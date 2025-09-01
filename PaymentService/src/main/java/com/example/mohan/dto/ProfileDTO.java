package com.example.mohan.dto;

import com.example.mohan.enums.AccountType;
import lombok.Data;

@Data
public class ProfileDTO {
    private Long id;
    private String accountNumber;
    private String firstName;
    private String lastName;
    private String email; // Add the email field here
    private String phoneNumber;
    private AccountType accountType;
    private double balance;
    private Long contactId;
}