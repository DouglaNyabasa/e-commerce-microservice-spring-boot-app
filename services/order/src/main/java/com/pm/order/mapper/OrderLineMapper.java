package com.pm.order.mapper;

import com.pm.order.dto.OrderLineRequest;
import com.pm.order.model.Order;
import com.pm.order.model.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {


    public Object toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.id())
                .quantity(request.quantity())
                .order(
                        Order.builder()
                                .id(request.orderId())
                                .build()
                )
                .productId(request.productId())
                .build();
    }
}
