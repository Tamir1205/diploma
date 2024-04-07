package com.example.flatsharing.payment.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentDTO {
    private String userId;
    private BigDecimal amount;
    private String advertisementId;
}
