package com.example.mohan.controller;

import com.example.mohan.dto.PaymentRequestDTO;
import com.example.mohan.dto.PaymentResponseDTO;
import com.example.mohan.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/doPayment")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponseDTO doPayment(@Valid @RequestBody PaymentRequestDTO paymentRequestDTO) {
        return paymentService.doPayment(paymentRequestDTO);
    }

    @GetMapping("/{paymentId}")
    public PaymentResponseDTO getPaymentDetails(@PathVariable Long paymentId) {
        return paymentService.getPaymentDetails(paymentId);
    }
    
    @GetMapping("/history")
    public List<PaymentResponseDTO> getPaymentHistory(@RequestParam Long userId) {
        return paymentService.getPaymentsForUser(userId);
    }
    
    @GetMapping("/history/{userId}")
    public List<PaymentResponseDTO> getPaymentsForUser(@PathVariable Long userId) {
        return paymentService.getPaymentsForUser(userId);
    }
}
