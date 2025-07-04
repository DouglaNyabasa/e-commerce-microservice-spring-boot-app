package com.pm.ecommerce.customer.dto;

import com.pm.ecommerce.customer.model.Address;


public record CustomerResponse(
        String id,
        String firstName,
        String lastName,

        String email,

        Address address
) {
}
