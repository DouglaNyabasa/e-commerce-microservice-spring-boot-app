package com.pm.ecommerce.product.exceptionHandling;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}
