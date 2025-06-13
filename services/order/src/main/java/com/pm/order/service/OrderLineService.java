package com.pm.order.service;

import com.pm.order.dto.OrderLineRequest;
import com.pm.order.mapper.OrderLineMapper;
import com.pm.order.repository.OrderLineRepository;

import org.springframework.stereotype.Service;

@Service

public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public OrderLineService(OrderLineRepository orderLineRepository, OrderLineMapper orderLineMapper) {
        this.orderLineRepository = orderLineRepository;
        this.orderLineMapper = orderLineMapper;
    }

//    public void saveOrderLine(OrderLineRequest request) {
//        var order = orderLineMapper.toOrderLine(request);
//        return orderLineRepository.save(order).getId();
//    }
}
