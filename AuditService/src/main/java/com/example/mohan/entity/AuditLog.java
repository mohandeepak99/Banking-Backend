package com.example.mohan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditId;

    @NotBlank
    @Column(length = 1000)
    private String action;

    @NotNull
    private LocalDateTime timestamp;

    @NotBlank
    @Column(length = 255)
    private String entityName;

    @NotBlank
    @Column(length = 255)
    private String entityId;

    @NotNull
    private Long customerId;

    @Column(length = 4000)
    private String details;
}
