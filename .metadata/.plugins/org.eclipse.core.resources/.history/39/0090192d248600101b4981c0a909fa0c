package com.example.mohan.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PaymentRequestDTO {

    @NotBlank(message = "Sender account number is required")
    private String senderAccountNumber;

    @NotBlank(message = "Sender name is required")
    private String senderName;

    @NotBlank(message = "Receiver account number is required")
    private String receiverAccountNumber;

    @NotBlank(message = "Receiver name is required")
    private String receiverName;

    @Positive(message = "Amount must be positive")
    private double amount;
}
