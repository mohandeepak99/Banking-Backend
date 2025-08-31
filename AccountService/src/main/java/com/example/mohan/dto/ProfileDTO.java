package com.example.mohan.dto;

import com.example.mohan.enums.AccountType;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProfileDTO {

    private Long profileId;

    @NotNull(message = "ContactId cannot be null")
    private Long contactId;

    @NotBlank(message = "Username is mandatory")
    @Size(max = 100)
    private String username;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Size(max = 150)
    private String email;

    @NotBlank(message = "Account Number is mandatory")
    @Size(max = 20)
    private String accountNumber;

    @NotNull(message = "Account Type is mandatory")
    private AccountType accountType;

    private boolean kyc;

    @PositiveOrZero(message = "Balance must be zero or greater")
    private double balance;

}
