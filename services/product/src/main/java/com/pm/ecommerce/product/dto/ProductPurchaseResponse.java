package com.pm.ecommerce.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
  Integer productId,
  String name,
  String description,
  BigDecimal price,
  double quantity


) {
}
