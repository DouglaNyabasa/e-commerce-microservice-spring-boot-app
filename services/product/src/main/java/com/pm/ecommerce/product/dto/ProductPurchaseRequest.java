package com.pm.ecommerce.product.dto;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(

        @NotNull(message = "Product is mandatory")
        Integer productId,
        @NotNull(message = "Quantity not null")
        double quantity
) {
}
