package com.example.mohan.service;

import com.example.mohan.dto.AuditLogDTO;
import com.example.mohan.dto.PaymentRequestDTO;
import com.example.mohan.dto.PaymentResponseDTO;
import com.example.mohan.dto.ProfileDTO;
import com.example.mohan.entity.Payment;
import com.example.mohan.enums.PaymentStatus;
import com.example.mohan.exception.PaymentException;
import com.example.mohan.feign.AccountFeignClient;
import com.example.mohan.feign.AuditClient;
import com.example.mohan.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final AccountFeignClient accountFeignClient;
    private final AuditClient auditClient;

    public PaymentResponseDTO doPayment(PaymentRequestDTO request) {

        // Validate sender account
        ProfileDTO senderAccount = accountFeignClient.getAccountByAccountNumber(request.getSenderAccountNumber());
        if (senderAccount == null) {
            throw new PaymentException("Sender account not found: " + request.getSenderAccountNumber());
        }

        // Validate receiver account
        ProfileDTO receiverAccount = accountFeignClient.getAccountByAccountNumber(request.getReceiverAccountNumber());
        if (receiverAccount == null) {
            throw new PaymentException("Receiver account not found: " + request.getReceiverAccountNumber());
        }

        // Check sufficient balance
        if (senderAccount.getBalance() < request.getAmount()) {
            throw new PaymentException("Insufficient balance in sender account");
        }

        // Proceed to create payment record
        Payment payment = new Payment();
        payment.setSenderAccountNumber(request.getSenderAccountNumber());
        payment.setSenderName(request.getSenderName());
        payment.setReceiverAccountNumber(request.getReceiverAccountNumber());
        payment.setReceiverName(request.getReceiverName());
        payment.setAmount(request.getAmount());
        payment.setPaymentTime(LocalDateTime.now());
        payment.setPaymentStatus(PaymentStatus.DONE);

        Payment saved = paymentRepository.save(payment);

        // Update account balances via Account Service
        accountFeignClient.debitAccount(request.getSenderAccountNumber(), request.getAmount());
        accountFeignClient.creditAccount(request.getReceiverAccountNumber(), request.getAmount());

        // Send audit log
        AuditLogDTO auditLogDTO = new AuditLogDTO();
        auditLogDTO.setAction("Payment Created");
        auditLogDTO.setEntityName("Payment");
        auditLogDTO.setEntityId(String.valueOf(saved.getPaymentId()));
        // Assuming senderAccount.getContactId()/customerId available; else fetch accordingly
        auditLogDTO.setCustomerId(senderAccount.getContactId());
        auditLogDTO.setTimestamp(LocalDateTime.now());
        auditLogDTO.setDetails(String.format("Amount: %.2f, From: %s, To: %s, Status: %s",
                saved.getAmount(), saved.getSenderAccountNumber(), saved.getReceiverAccountNumber(), saved.getPaymentStatus()));

        auditClient.sendAuditLog(auditLogDTO);

        // Map to response DTO
        PaymentResponseDTO response = new PaymentResponseDTO();
        response.setPaymentId(saved.getPaymentId());
        response.setSenderAccountNumber(saved.getSenderAccountNumber());
        response.setReceiverAccountNumber(saved.getReceiverAccountNumber());
        response.setPaymentTime(saved.getPaymentTime());
        response.setAmount(saved.getAmount());
        response.setPaymentStatus(saved.getPaymentStatus());

        return response;
    }

    public PaymentResponseDTO getPaymentDetails(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentException("Payment not found for id: " + paymentId));
        PaymentResponseDTO dto = new PaymentResponseDTO();
        dto.setPaymentId(payment.getPaymentId());
        dto.setSenderAccountNumber(payment.getSenderAccountNumber());
        dto.setReceiverAccountNumber(payment.getReceiverAccountNumber());
        dto.setPaymentTime(payment.getPaymentTime());
        dto.setAmount(payment.getAmount());
        dto.setPaymentStatus(payment.getPaymentStatus());
        return dto;
    }
}
