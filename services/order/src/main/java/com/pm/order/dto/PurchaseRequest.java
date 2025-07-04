package com.pm.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(

    @NotNull(message = "Product is mandatory")
    Integer productId,

    @Positive( message = "Product is mandatory")
    double quantity

) {
}
