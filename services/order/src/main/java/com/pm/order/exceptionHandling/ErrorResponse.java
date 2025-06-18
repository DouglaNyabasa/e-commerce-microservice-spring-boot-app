package com.pm.order.exceptionHandling;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}
