package com.pm.ecommerce.product.resource;


import com.pm.ecommerce.product.dto.ProductPurchaseRequest;
import com.pm.ecommerce.product.dto.ProductPurchaseResponse;
import com.pm.ecommerce.product.dto.ProductRequest;
import com.pm.ecommerce.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products ")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok(productService.createProduct(productRequest));

    }

    @PostMapping("/purchase")
    public  ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(@RequestBody List<ProductPurchaseRequest> productPurchaseRequest){
        return ResponseEntity.ok(productService.purchaseProducts(productPurchaseRequest));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductPurchaseResponse> findProductById(@PathVariable("product-id") Integer productId){
         return ResponseEntity.ok(productService.findProductById(productId));
    }

    @GetMapping
    public  ResponseEntity<List<ProductPurchaseResponse>> findAllProducts(){
        return ResponseEntity.ok(productService.findAllProducts());
    }

}
