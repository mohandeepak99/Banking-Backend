package com.example.mohan.controller;

import com.example.mohan.dto.ContactInfoDTO;
import com.example.mohan.service.ContactInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class ContactInfoController {

    private final ContactInfoService contactInfoService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactInfoDTO saveContactInfo(@Valid @RequestBody ContactInfoDTO contactInfoDTO) {
        return contactInfoService.saveContactInfo(contactInfoDTO);
    }

    @GetMapping("/{id}")
    public ContactInfoDTO getContactInfoById(@PathVariable Long id) {
        return contactInfoService.getContactInfoById(id);
    }

    @PutMapping("/update")
    public ContactInfoDTO updateContactInfo(@Valid @RequestBody ContactInfoDTO contactInfoDTO) {
        return contactInfoService.updateContactInfo(contactInfoDTO);
    }
}
