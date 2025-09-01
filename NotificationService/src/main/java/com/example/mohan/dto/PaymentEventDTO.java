package com.example.mohan.dto;

import com.example.mohan.enums.PaymentStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentEventDTO {
    private Long paymentId;
    private String senderAccountNumber;
    private String senderEmail;
    private String receiverAccountNumber;
    private double amount;
    private LocalDateTime paymentTime;
    private PaymentStatus paymentStatus;
}