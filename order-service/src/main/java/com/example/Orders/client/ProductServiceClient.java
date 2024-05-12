package com.example.Orders.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(name = "Product-catalog-service")
public interface ProductServiceClient {

    @GetMapping("/products/{id}")
    ProductDtoResponse getProductById(@PathVariable("id") Long id);
}
