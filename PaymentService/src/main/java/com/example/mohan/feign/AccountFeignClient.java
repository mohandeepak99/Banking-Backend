package com.example.mohan.feign;

import com.example.mohan.dto.ProfileDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "account-service", url = "${account.service.url}")
public interface AccountFeignClient {

    @GetMapping("/accountNumber/{accountNumber}")
    ProfileDTO getAccountByAccountNumber(@PathVariable("accountNumber") String accountNumber);

    @PostMapping("/debit")
    void debitAccount(@RequestParam String accountNumber, @RequestParam double amount);

    @PostMapping("/credit")
    void creditAccount(@RequestParam String accountNumber, @RequestParam double amount);
}
