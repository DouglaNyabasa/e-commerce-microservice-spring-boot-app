package com.pm.ecommerce.product.exceptionHandling;

public class ProductPurchaseException extends RuntimeException {
    public ProductPurchaseException(String message) {
        super(message);
    }
}
