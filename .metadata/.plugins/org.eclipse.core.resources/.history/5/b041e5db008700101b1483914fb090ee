package com.example.mohan.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NotificationRequestDTO {

    @NotBlank(message = "Recipient email is mandatory")
    @Email(message = "Valid email required")
    private String recipientEmail;

    @NotBlank(message = "Subject is mandatory")
    @Size(max = 150)
    private String subject;

    @NotBlank(message = "Body is mandatory")
    private String body;
}
