package com.pm.order.service;

import com.pm.order.customer.CustomerClient;
import com.pm.order.dto.*;
import com.pm.order.exception.BusinessException;
import com.pm.order.kafka.OrderProducer;
import com.pm.order.mapper.OrderMapper;
import com.pm.order.model.OrderLine;
import com.pm.order.payment.PaymentClient;
import com.pm.order.product.ProductClient;
import com.pm.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final CustomerClient customerClient;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public OrderService(CustomerClient customerClient, OrderRepository orderRepository, OrderMapper mapper, ProductClient productClient, OrderLineService orderLineService, OrderProducer orderProducer, PaymentClient paymentClient) {
        this.customerClient = customerClient;
        this.orderRepository = orderRepository;
        this.mapper = mapper;
        this.productClient = productClient;
        this.orderLineService = orderLineService;
        this.orderProducer = orderProducer;
        this.paymentClient = paymentClient;
    }

    public Integer createOrder(@Valid OrderRequest request) {

        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(()-> new BusinessException("Cannot create order ::No Customer does not exists with the provided ID"));
        var purchasedProducts = this.productClient.purchaseProducts(request.products());

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
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer

        );
        paymentClient.requestOrderPayment(paymentRequest);
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );


        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findByOrderId(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(()-> new EntityNotFoundException(String.format("Order with ID %s not found", orderId)));
    }
}
