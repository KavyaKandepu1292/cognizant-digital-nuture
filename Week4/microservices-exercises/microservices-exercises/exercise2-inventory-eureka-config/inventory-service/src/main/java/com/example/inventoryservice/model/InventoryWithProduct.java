package com.example.inventoryservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InventoryWithProduct {
    private InventoryItem inventoryItem;
    private ProductDto product;
}
