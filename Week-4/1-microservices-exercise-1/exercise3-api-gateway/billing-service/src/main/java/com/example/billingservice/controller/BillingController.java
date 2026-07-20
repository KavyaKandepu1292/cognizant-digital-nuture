package com.example.billingservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/** Internal path "/billing/**" — gateway rewrites "/api/billing/**" down to this. */
@RestController
@RequestMapping("/billing")
public class BillingController {

    @GetMapping("/{id}")
    public Map<String, Object> getBillingRecord(@PathVariable Long id) {
        return Map.of(
                "id", id,
                "amountDue", 42.50,
                "servedBy", "billing-service"
        );
    }
}
