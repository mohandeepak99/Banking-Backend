package com.example.mohan.service;

import com.example.mohan.dto.AuditLogDTO;
import com.example.mohan.dto.ContactInfoDTO;
import com.example.mohan.entity.ContactInfo;
import com.example.mohan.exception.CustomerNotFoundException;
import com.example.mohan.feign.AuditClient;
import com.example.mohan.repository.ContactInfoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ContactInfoService {

    private final ContactInfoRepository contactInfoRepository;
    private final AuditClient auditClient;

    public ContactInfoDTO saveContactInfo(@Valid ContactInfoDTO contactInfoDTO) {
        ContactInfo contactInfo = toEntity(contactInfoDTO);
        ContactInfo saved = contactInfoRepository.save(contactInfo);
        sendAuditLog("ContactInfo Created", "ContactInfo",
                String.valueOf(saved.getContactId()), saved.getContactId(), "Created new contact info");
        return toDTO(saved);
    }

    public ContactInfoDTO getContactInfoById(Long id) {
        ContactInfo contactInfo = contactInfoRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        return toDTO(contactInfo);
    }

    public ContactInfoDTO updateContactInfo(@Valid ContactInfoDTO contactInfoDTO) {
        if (contactInfoDTO.getContactId() == null || !contactInfoRepository.existsById(contactInfoDTO.getContactId())) {
            throw new CustomerNotFoundException("Customer not found with id: " + contactInfoDTO.getContactId());
        }
        ContactInfo contactInfo = toEntity(contactInfoDTO);
        ContactInfo updated = contactInfoRepository.save(contactInfo);
        sendAuditLog("ContactInfo Updated", "ContactInfo",
                String.valueOf(updated.getContactId()), updated.getContactId(), "Updated contact info");
        return toDTO(updated);
    }

    private ContactInfo toEntity(ContactInfoDTO dto) {
        ContactInfo contact = new ContactInfo();
        contact.setContactId(dto.getContactId());
        contact.setFirstName(dto.getFirstName());
        contact.setLastName(dto.getLastName());
        contact.setEmail(dto.getEmail());
        contact.setMobileNumber(dto.getMobileNumber());
        contact.setAddress(dto.getAddress());
        contact.setAge(dto.getAge());
        contact.setGender(dto.getGender());
        contact.setKyc(dto.isKyc());
        return contact;
    }

    private ContactInfoDTO toDTO(ContactInfo entity) {
        ContactInfoDTO dto = new ContactInfoDTO();
        dto.setContactId(entity.getContactId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setMobileNumber(entity.getMobileNumber());
        dto.setAddress(entity.getAddress());
        dto.setAge(entity.getAge());
        dto.setGender(entity.getGender());
        dto.setKyc(entity.isKyc());
        return dto;
    }

    private void sendAuditLog(String action, String entityName, String entityId,
                              Long customerId, String details) {
        AuditLogDTO auditLogDTO = new AuditLogDTO();
        auditLogDTO.setAction(action);
        auditLogDTO.setEntityName(entityName);
        auditLogDTO.setEntityId(entityId);
        auditLogDTO.setCustomerId(customerId);
        auditLogDTO.setTimestamp(LocalDateTime.now());
        auditLogDTO.setDetails(details);
        auditClient.sendAuditLog(auditLogDTO);
    }
}
