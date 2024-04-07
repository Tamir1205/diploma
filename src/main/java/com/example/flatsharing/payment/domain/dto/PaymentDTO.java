package com.example.flatsharing.payment.domain.dto;

import com.example.flatsharing.payment.domain.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private String id;
    private String userId;
    private String advertisementId;
    private Payment.PaymentName paymentName;
}
