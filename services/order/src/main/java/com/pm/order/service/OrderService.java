package com.pm.order.service;

import com.pm.order.dto.OrderRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final CustomerClient customerClient;

    public Integer createOrder(@Valid OrderRequest request) {


        return null;
    }
}
