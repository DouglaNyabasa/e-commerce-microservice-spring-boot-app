package com.pm.ecommerce.customer.exceptionHandling;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}
