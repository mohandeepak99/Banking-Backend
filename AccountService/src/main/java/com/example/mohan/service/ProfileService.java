package com.example.mohan.service;


import com.example.mohan.dto.CustomerDTO;
import com.example.mohan.dto.ProfileDTO;
import com.example.mohan.entity.Profile;
import com.example.mohan.exception.ProfileNotFoundException;

import com.example.mohan.feign.CustomerFeignClient;
import com.example.mohan.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final CustomerFeignClient customerFeignClient;


    public ProfileDTO saveProfile(ProfileDTO profileDTO) {

        CustomerDTO customer = customerFeignClient.getCustomerById(profileDTO.getContactId());

        if (customer == null) {
            throw new ProfileNotFoundException("Customer not found with id: " + profileDTO.getContactId());
        }

        Profile profile = new Profile();
        profile.setContactId(profileDTO.getContactId());
        profile.setUsername(profileDTO.getUsername());
        profile.setEmail(profileDTO.getEmail());
        profile.setAccountNumber(profileDTO.getAccountNumber());
        profile.setAccountType(profileDTO.getAccountType());
        profile.setKyc(profileDTO.isKyc());
        profile.setBalance(profileDTO.getBalance());

        Profile saved = profileRepository.save(profile);

        

        return mapToDTO(saved);
    }

    public ProfileDTO getProfileById(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ProfileNotFoundException("Account not found with id: " + profileId));
        return mapToDTO(profile);
    }

    public ProfileDTO getProfileByAccountNumber(String accountNumber) {
        Profile profile = profileRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ProfileNotFoundException("Account not found with account number: " + accountNumber));
        return mapToDTO(profile);
    }

    public Page<ProfileDTO> getAllProfiles(Pageable pageable) {
        return profileRepository.findAll(pageable).map(this::mapToDTO);
    }

    public ProfileDTO debitAccountBalance(String accountNumber, double amount) {
        Profile profile = profileRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ProfileNotFoundException("Account not found: " + accountNumber));

        if (profile.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        profile.setBalance(profile.getBalance() - amount);
        Profile updated = profileRepository.save(profile);

        

        return mapToDTO(updated);
    }

    public ProfileDTO creditAccountBalance(String accountNumber, double amount) {
        Profile profile = profileRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ProfileNotFoundException("Account not found: " + accountNumber));

        profile.setBalance(profile.getBalance() + amount);
        Profile updated = profileRepository.save(profile);

        

        return mapToDTO(updated);
    }


    private ProfileDTO mapToDTO(Profile profile) {
        ProfileDTO dto = new ProfileDTO();
        dto.setProfileId(profile.getProfileId());
        dto.setContactId(profile.getContactId());
        dto.setUsername(profile.getUsername());
        dto.setEmail(profile.getEmail());
        dto.setAccountNumber(profile.getAccountNumber());
        dto.setAccountType(profile.getAccountType());
        dto.setKyc(profile.isKyc());
        dto.setBalance(profile.getBalance());
        return dto;
    }

    
    
    public void deleteProfileById(Long profileId) {
    	Profile profile = profileRepository.findById(profileId)
    		    .orElseThrow(() -> new ProfileNotFoundException("Account not found with id: " + profileId));

    		Long customerId = profile.getContactId();
    		profileRepository.deleteById(profileId);
    		

    }

    public List<ProfileDTO> getProfilesByContactId(Long contactId) {
        return profileRepository.findByContactId(contactId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

}
