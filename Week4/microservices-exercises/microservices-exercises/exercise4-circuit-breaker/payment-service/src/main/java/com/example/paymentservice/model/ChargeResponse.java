package com.example.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChargeResponse {
    private String orderId;
    private String status;   // e.g. SUCCESS, PENDING_FALLBACK
    private String message;
}
