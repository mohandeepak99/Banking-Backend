package com.example.mohan.feign;

import com.example.mohan.dto.AuditLogDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "audit-service", url = "${audit.service.url}")
public interface AuditClient {

    @PostMapping("/api/v1/audit/log")
    void sendAuditLog(@RequestBody AuditLogDTO auditLogDTO);
}
