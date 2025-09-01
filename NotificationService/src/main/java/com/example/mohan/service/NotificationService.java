package com.example.mohan.service;

import com.example.mohan.dto.PaymentEventDTO; // Use the new DTO
import com.example.mohan.exception.NotificationException;
import com.example.mohan.util.EmailSenderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final EmailSenderUtil emailSenderUtil;

    public void sendPaymentEmailNotification(PaymentEventDTO request) {
        try {
            String to = request.getSenderEmail(); // Use the email from the DTO
            String subject = "Payment Confirmation";
            String body = String.format("Payment with ID: %d, Amount: %.2f, Status: %s",
                    request.getPaymentId(), request.getAmount(), request.getPaymentStatus());
            emailSenderUtil.sendEmail(to, subject, body);
        } catch (Exception e) {
            throw new NotificationException("Failed to send notification for payment ID: " + request.getPaymentId(), e);
        }
    }
}