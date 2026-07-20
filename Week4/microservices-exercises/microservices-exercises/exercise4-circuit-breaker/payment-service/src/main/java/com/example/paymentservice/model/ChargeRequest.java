package com.example.paymentservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChargeRequest {
    @NotBlank
    private String orderId;

    @Positive
    private double amount;

    @NotBlank
    private String cardToken;
}
