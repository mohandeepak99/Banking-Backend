package com.example.mohan.service;

import com.example.mohan.dto.AuditLogDTO;
import com.example.mohan.entity.AuditLog;
import com.example.mohan.exception.AuditException;
import com.example.mohan.repository.AuditRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditRepository auditRepository;

    public AuditLogDTO saveAuditLog(@Valid AuditLogDTO auditLogDTO) {
        try {
            AuditLog auditLog = new AuditLog();
            auditLog.setAction(auditLogDTO.getAction());
            auditLog.setEntityName(auditLogDTO.getEntityName());
            auditLog.setEntityId(auditLogDTO.getEntityId());
            auditLog.setCustomerId(auditLogDTO.getCustomerId());
            auditLog.setDetails(auditLogDTO.getDetails());
            auditLog.setTimestamp(auditLogDTO.getTimestamp() != null ? auditLogDTO.getTimestamp() : LocalDateTime.now());

            AuditLog saved = auditRepository.save(auditLog);

            auditLogDTO.setTimestamp(saved.getTimestamp());
            return auditLogDTO;
        } catch (Exception e) {
            throw new AuditException("Failed to save audit log: " + e.getMessage());
        }
    }
    
    public Page<AuditLogDTO> getAllAuditLogs(Pageable pageable) {
        return auditRepository.findAll(pageable)
            .map(this::mapToDTO);
    }

    private AuditLogDTO mapToDTO(AuditLog entity) {
        AuditLogDTO dto = new AuditLogDTO();
        dto.setAction(entity.getAction());
        dto.setEntityName(entity.getEntityName());
        dto.setEntityId(entity.getEntityId());
        dto.setCustomerId(entity.getCustomerId());
        dto.setTimestamp(entity.getTimestamp());
        // Optionally omit or include a short version of details
        return dto;
    }

}
