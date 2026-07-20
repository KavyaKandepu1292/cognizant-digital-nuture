package com.example.inventoryservice.service;

import com.example.inventoryservice.client.ProductClient;
import com.example.inventoryservice.model.InventoryItem;
import com.example.inventoryservice.model.InventoryWithProduct;
import com.example.inventoryservice.model.ProductDto;
import com.example.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductClient productClient;

    public InventoryItem createItem(InventoryItem item) {
        return inventoryRepository.save(item);
    }

    public InventoryWithProduct getByProductId(Long productId) {
        InventoryItem item = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("No inventory for productId: " + productId));
        ProductDto product = productClient.getProductById(productId);
        return new InventoryWithProduct(item, product);
    }

    public InventoryItem adjustQuantity(Long productId, int delta) {
        InventoryItem item = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("No inventory for productId: " + productId));
        item.setQuantityOnHand(item.getQuantityOnHand() + delta);
        return inventoryRepository.save(item);
    }
}
