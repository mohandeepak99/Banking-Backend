package com.example.mohan.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuditLogDTO {

    private String action;
    private String entityName;
    private String entityId;
    private Long customerId;
    private LocalDateTime timestamp;
    private String details;
}
