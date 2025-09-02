package com.example.mohan.repository;

import com.example.mohan.entity.Payment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	/** NEW: fetch payments where either sender or receiver matches any of the given account numbers */
    List<Payment> findBySenderAccountNumberInOrReceiverAccountNumberIn(
        List<String> senderAccountNumbers,
        List<String> receiverAccountNumbers);
}
