package com.example.mohan.entity;

import com.example.mohan.enums.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @NotBlank
    private String senderAccountNumber;

    @NotBlank
    private String senderName;

    @NotBlank
    private String receiverAccountNumber;

    @NotBlank
    private String receiverName;

    @NotNull
    private LocalDateTime paymentTime;

    @Positive
    private double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
