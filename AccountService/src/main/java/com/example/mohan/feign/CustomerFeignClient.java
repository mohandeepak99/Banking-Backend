package com.example.mohan.feign;

import com.example.mohan.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "${customer.service.url}")
public interface CustomerFeignClient {

    @GetMapping("/{id}")
    CustomerDTO getCustomerById(@PathVariable("id") Long id);
}
