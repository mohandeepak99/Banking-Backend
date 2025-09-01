package com.example.mohan.kafka;

import com.example.mohan.dto.PaymentEventDTO; // Use the new DTO
import com.example.mohan.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "payment-notification-topic", groupId = "notification-group")
    public void consumePaymentNotification(PaymentEventDTO paymentEventDTO) {
        log.info("Received payment notification for payment ID: {}", paymentEventDTO.getPaymentId());
        notificationService.sendPaymentEmailNotification(paymentEventDTO);
    }
}