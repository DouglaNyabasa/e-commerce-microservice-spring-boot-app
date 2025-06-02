package com.pm.ecommerce.product.service;


import com.pm.ecommerce.product.dto.ProductPurchaseRequest;
import com.pm.ecommerce.product.dto.ProductPurchaseResponse;
import com.pm.ecommerce.product.dto.ProductRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    public Integer createProduct(@Valid ProductRequest productRequest) {
        return null;
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> productPurchaseRequest) {
        return null;
    }

    public ProductPurchaseResponse findProductById(Integer productId) {
        return null;
    }

    public List<ProductPurchaseResponse> findAllProducts() {
        return null;
    }
}
