package com.example.mohan.entity;

import com.example.mohan.enums.AccountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    @NotNull(message = "Contact ID is mandatory")
    private Long contactId;

    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Enter a valid email")
    private String email;

    @NotBlank(message = "Account Number is mandatory")
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private boolean kyc;

    @PositiveOrZero(message = "Balance cannot be negative")
    private double balance;
}
