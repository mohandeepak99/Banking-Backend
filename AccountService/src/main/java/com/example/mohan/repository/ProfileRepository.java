package com.example.mohan.repository;

import com.example.mohan.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByAccountNumber(String accountNumber);
    List<Profile> findByContactId(Long contactId);
}
