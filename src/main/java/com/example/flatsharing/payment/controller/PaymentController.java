package com.example.flatsharing.payment.controller;

import com.example.flatsharing.payment.domain.dto.CreatePaymentDTO;
import com.example.flatsharing.payment.domain.dto.PaymentDTO;
import com.example.flatsharing.payment.domain.model.Payment;
import com.example.flatsharing.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public PaymentDTO createPayment(@RequestBody CreatePaymentDTO dto) {
        Payment payment = paymentService.createPayment(dto);
        return new PaymentDTO(payment.getId(), payment.getUserId(), payment.getAdvertisementId(),
                payment.getPaymentType().getName());
    }
}
