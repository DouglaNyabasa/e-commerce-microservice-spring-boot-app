package com.pm.order.dto;


public record OrderLineRequest  (
        Integer id,

        Integer orderId,
        Integer productId,

        double quantity) {


}