package com.ecommerce.pm.notifications.repository;

import com.ecommerce.pm.notifications.kafka.payment.PaymentConfirmation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<PaymentConfirmation, String> {
}
