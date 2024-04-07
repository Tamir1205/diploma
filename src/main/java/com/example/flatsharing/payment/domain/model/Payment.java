package com.example.flatsharing.payment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Document("payments")
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private String id;
    private String userId;
    private PaymentType paymentType;
    private String advertisementId;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaymentType {
        private BigDecimal amount;
        private PaymentName name;
        private Period duration;
    }

    public enum PaymentName {
        FOR_3_DAYS,
        FOR_7_DAYS,
        FOR_10_DAYS
    }
}
