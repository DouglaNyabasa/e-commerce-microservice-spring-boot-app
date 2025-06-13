package com.pm.order.service;

import com.pm.order.customer.CustomerClient;
import com.pm.order.dto.OrderRequest;
import com.pm.order.dto.PurchaseRequest;
import com.pm.order.exception.BusinessException;
import com.pm.order.mapper.OrderMapper;
import com.pm.order.model.OrderLine;
import com.pm.order.dto.OrderLineRequest;
import com.pm.order.product.ProductClient;
import com.pm.order.repository.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final CustomerClient customerClient;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;

    public OrderService(CustomerClient customerClient, OrderRepository orderRepository, OrderMapper mapper, ProductClient productClient, OrderLineService orderLineService) {
        this.customerClient = customerClient;
        this.orderRepository = orderRepository;
        this.mapper = mapper;
        this.productClient = productClient;
        this.orderLineService = orderLineService;
    }

    public Integer createOrder(@Valid OrderRequest request) {

        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(()-> new BusinessException("Cannot create order ::No Customer does not exists with the provided ID"));
        this.productClient.purchaseProducts(request.products());

        var order = this.orderRepository.save(mapper.toOrder(request));

        for (PurchaseRequest purchaseRequest: request.products()){
          orderLineService.saveOrderLine(
                  new OrderLineRequest(
                          null,
                          order.getId(),
                          purchaseRequest.productId(),
                          purchaseRequest.quantity()
                  )
          );
        }


        return null;
    }
}
