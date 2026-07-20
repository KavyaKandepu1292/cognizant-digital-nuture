package com.example.inventoryservice.client;

import com.example.inventoryservice.model.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Resolved purely by Eureka service name ("product-service") — no hardcoded
 * host/port. This is the payoff of service discovery: as long as product-service
 * is registered with Eureka, this call works regardless of where it's deployed.
 */
@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/api/products/{id}")
    ProductDto getProductById(@PathVariable("id") Long id);
}
