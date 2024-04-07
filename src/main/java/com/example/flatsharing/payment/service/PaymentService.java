package com.example.flatsharing.payment.service;

import com.example.flatsharing.advertisement.service.AdvertisementService;
import com.example.flatsharing.payment.domain.dto.CreatePaymentDTO;
import com.example.flatsharing.payment.domain.model.Payment;
import com.example.flatsharing.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.joda.time.Period;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

import static com.example.flatsharing.payment.domain.model.Payment.PaymentName.*;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final AdvertisementService advertisementService;

    @Transactional
    public Payment createPayment(CreatePaymentDTO dto) {
        Payment payment = new Payment();
        payment.setId(UUID.randomUUID().toString());
        payment.setUserId(dto.getUserId());
        payment.setAdvertisementId(dto.getAdvertisementId());
        if (dto.getAmount().intValue() == 200) {
            payment.setPaymentType(new Payment.PaymentType(BigDecimal.valueOf(200), FOR_3_DAYS, new Period().plusDays(3)));
        } else if (dto.getAmount().intValue() == 500) {
            payment.setPaymentType(new Payment.PaymentType(BigDecimal.valueOf(500), FOR_7_DAYS, new Period().plusDays(7)));
        } else if (dto.getAmount().intValue() == 1000) {
            payment.setPaymentType(new Payment.PaymentType(BigDecimal.valueOf(1000), FOR_10_DAYS, new Period().plusDays(10)));
        } else {
            throw new ArithmeticException("Not right price");
        }
        paymentRepository.save(payment);
        advertisementService.promote(dto.getAdvertisementId());
        return payment;
    }
}
