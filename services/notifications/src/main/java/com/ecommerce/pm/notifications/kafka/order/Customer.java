package com.ecommerce.pm.notifications.kafka.order;

public record Customer(

        String id,

        String firstName,
        String lastName,
        String email
) {
}
