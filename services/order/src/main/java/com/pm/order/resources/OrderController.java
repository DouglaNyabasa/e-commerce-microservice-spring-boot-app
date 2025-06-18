package com.pm.order.resources;

import com.pm.order.dto.OrderRequest;
import com.pm.order.dto.OrderResponse;
import com.pm.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    public ResponseEntity< Integer> createOrder(@RequestBody @Valid OrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> findByOrderId(@PathVariable("order-id") Integer orderId) {
        return ResponseEntity.ok(orderService.findByOrderId(orderId));

    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        return ResponseEntity.ok(orderService.findAll());

    }
}
