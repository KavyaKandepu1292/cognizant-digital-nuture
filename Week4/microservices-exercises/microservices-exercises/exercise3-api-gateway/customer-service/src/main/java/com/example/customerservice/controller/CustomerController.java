package com.example.customerservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Note: internal path is "/customers/**" — the gateway rewrites the external
 * "/api/customers/**" prefix down to this before forwarding the request.
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @GetMapping("/{id}")
    public Map<String, Object> getCustomer(@PathVariable Long id) {
        return Map.of(
                "id", id,
                "name", "Customer #" + id,
                "servedBy", "customer-service"
        );
    }
}
