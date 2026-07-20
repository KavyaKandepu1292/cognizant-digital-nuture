package com.example.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/** Order enriched with the user info fetched from user-service. */
@Data
@AllArgsConstructor
public class OrderDetails {
    private Order order;
    private UserDto user;
}
