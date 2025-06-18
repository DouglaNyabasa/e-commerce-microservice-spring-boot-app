package com.pm.payment.mapper;

import com.pm.payment.dto.PaymentRequest;
import com.pm.payment.model.Payment;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;


@Service
public class PaymentMapper {
    public Payment toPayment(@Valid PaymentRequest paymentRequest) {
        return Payment.builder()
                .id(paymentRequest.id())
                .orderId(paymentRequest.orderId())
                .paymentMethod(paymentRequest.paymentMethod())
                .amount(paymentRequest.amount())
                .build();
    }
}
