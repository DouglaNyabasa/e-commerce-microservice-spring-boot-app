package com.pm.ecommerce.customer.mapper;

import com.pm.ecommerce.customer.dto.CustomerResponse;

import com.pm.ecommerce.customer.dto.CustomerRequest;
import com.pm.ecommerce.customer.model.Customer;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service

public class CustomerMapper {
    public Customer toCustomer(@Valid CustomerRequest request) {
        if (request == null) {
            return null;
        }
        return Customer.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();

    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );

    }
}
