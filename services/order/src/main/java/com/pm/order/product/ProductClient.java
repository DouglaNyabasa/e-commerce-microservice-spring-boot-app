package com.pm.order.product;

import com.pm.order.dto.PurchaseRequest;
import com.pm.order.dto.PurchaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productUrl;

    private final RestTemplate restTemplate;
//
//    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestsBody){
//        HttpHeaders headers = new HttpHeaders();
//        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
//
//    }
}
