package com.example.mohan.dto;

import com.example.mohan.enums.PaymentStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentResponseDTO {

    private Long paymentId;
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private LocalDateTime paymentTime;
    private double amount;
    private PaymentStatus paymentStatus;
}
