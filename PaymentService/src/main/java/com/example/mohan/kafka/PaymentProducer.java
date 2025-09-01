package com.example.mohan.kafka;

import com.example.mohan.dto.PaymentEventDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentProducer {

    private static final String TOPIC = "payment-notification-topic";

    private final KafkaTemplate<String, PaymentEventDTO> kafkaTemplate;

    public void sendPaymentEvent(PaymentEventDTO paymentEventDTO) {
        log.info("Sending payment notification for payment ID: {} to Kafka topic: {}", paymentEventDTO.getPaymentId(), TOPIC);
        kafkaTemplate.send(TOPIC, paymentEventDTO);
    }
}