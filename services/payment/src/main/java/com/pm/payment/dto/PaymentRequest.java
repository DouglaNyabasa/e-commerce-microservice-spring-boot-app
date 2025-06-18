package com.pm.payment.dto;

import com.pm.payment.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
         BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer

) {
}
