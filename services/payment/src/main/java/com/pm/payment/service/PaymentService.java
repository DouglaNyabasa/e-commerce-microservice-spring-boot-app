package com.pm.payment.service;

import com.pm.payment.dto.PaymentRequest;
import com.pm.payment.mapper.PaymentMapper;
import com.pm.payment.model.Payment;
import com.pm.payment.notification.NotificationProducer;
import com.pm.payment.notification.PaymentNotificationRequest;
import com.pm.payment.repository.PaymentRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;

    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper, NotificationProducer notificationProducer) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.notificationProducer = notificationProducer;
    }

    public Integer createPayment(@Valid PaymentRequest paymentRequest) {

        var payment = paymentRepository.save(paymentMapper.toPayment(paymentRequest));
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        paymentRequest.orderReference(),
                        paymentRequest.amount(),
                        paymentRequest.paymentMethod(),
                        paymentRequest.customer().firstName(),
                        paymentRequest.customer().lastName(),
                        paymentRequest.customer().email()
                )
        );

        return payment.getId();
    }
}
