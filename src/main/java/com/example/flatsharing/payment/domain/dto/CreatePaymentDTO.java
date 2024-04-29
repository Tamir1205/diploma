package com.example.flatsharing.payment.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentDTO {
    @NotEmpty
    private String userId;
    @NotEmpty
    private BigDecimal amount;
    private String advertisementId;
}
