package com.example.mohan.entity;

import com.example.mohan.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
@Table(name = "contact_info")
public class ContactInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;

    @NotBlank(message = "First name is mandatory")
    @Size(max = 100)
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(max = 100)
    @Column(nullable = false)
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Enter a valid email")
    @Size(max = 255)
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Mobile number is mandatory")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    @Column(nullable = false)
    private String mobileNumber;

    @NotBlank(message = "Address is mandatory")
    @Size(max = 255)
    @Column(nullable = false)
    private String address;

    @NotNull(message = "Age is mandatory")
    @Min(value = 18)
    @Max(value = 120)
    @Column(nullable = false)
    private Integer age;

    @NotNull(message = "Gender is mandatory")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private boolean kyc;
}
