package com.pm.order.service;

import com.pm.order.customer.CustomerClient;
import com.pm.order.dto.OrderRequest;
import com.pm.order.exception.BusinessException;
import com.pm.order.product.ProductClient;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;

    public OrderService(CustomerClient customerClient, ProductClient productClient) {
        this.customerClient = customerClient;
        this.productClient = productClient;
    }

    public Integer createOrder(@Valid OrderRequest request) {

        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(()-> new BusinessException("Cannot create order ::No Customer does not exists with the provided ID"));


        return null;
    }
}
