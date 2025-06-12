package com.pm.ecommerce.product.service;


import com.pm.ecommerce.product.dto.ProductPurchaseRequest;
import com.pm.ecommerce.product.dto.ProductPurchaseResponse;
import com.pm.ecommerce.product.dto.ProductRequest;
import com.pm.ecommerce.product.dto.ProductResponse;
import com.pm.ecommerce.product.exceptionHandling.ProductPurchaseException;
import com.pm.ecommerce.product.mapper.ProductMapper;
import com.pm.ecommerce.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public Integer createProduct(@Valid ProductRequest productRequest) {
         var product = productMapper.toProduct(productRequest);
         return productRepository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> productPurchaseRequest) {
        var productIds = productPurchaseRequest
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        var storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if(productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more Products does not exists");
        }
        var storedRequest = productPurchaseRequest
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for(int i = 0; i < storedRequest.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = storedRequest.get(i);

            if(product.getAvailableQuantity() < productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient stock Quantity for product with ID::"+ productRequest.productId());
            }
            var availableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(availableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product,productRequest.quantity()));
        }
        return purchasedProducts;

    }

    public ProductResponse findProductById(Integer productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(()-> new EntityNotFoundException("Product not found with ID :"+ productId));
    }

    public List<ProductResponse> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper:: toProductResponse)
                .collect(Collectors.toList());
    }
}
