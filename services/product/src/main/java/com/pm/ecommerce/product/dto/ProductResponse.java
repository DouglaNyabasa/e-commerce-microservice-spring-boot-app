package com.pm.ecommerce.product.dto;

import java.math.BigDecimal;

public record ProductResponse(
        Integer id,
        String name,
        String description,

        BigDecimal price,
        double availableQuantity,
        Integer categoryId,

        String categoryName,
        String categoryDescription
) {
}
