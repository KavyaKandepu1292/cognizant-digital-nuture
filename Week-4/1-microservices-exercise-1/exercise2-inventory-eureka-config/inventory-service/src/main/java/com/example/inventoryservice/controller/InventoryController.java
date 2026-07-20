package com.example.inventoryservice.controller;

import com.example.inventoryservice.model.InventoryItem;
import com.example.inventoryservice.model.InventoryWithProduct;
import com.example.inventoryservice.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryItem> createItem(@Valid @RequestBody InventoryItem item) {
        return new ResponseEntity<>(inventoryService.createItem(item), HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<InventoryWithProduct> getByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(inventoryService.getByProductId(productId));
    }

    @PatchMapping("/product/{productId}/adjust")
    public ResponseEntity<InventoryItem> adjustQuantity(@PathVariable Long productId,
                                                          @RequestParam int delta) {
        return ResponseEntity.ok(inventoryService.adjustQuantity(productId, delta));
    }
}
