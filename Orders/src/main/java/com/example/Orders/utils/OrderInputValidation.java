package com.example.Orders.utils;

import com.example.Orders.dto.OrderRequestDto;
import com.example.Orders.exception.InvalidOrderException;
import org.springframework.stereotype.Component;

@Component
public class OrderInputValidation {
    public void validateOrderRequest(OrderRequestDto orderRequestDto) {
        if (orderRequestDto == null) {
            throw new InvalidOrderException("Order request cannot be null");
        }
        if (orderRequestDto.getItems() == null || orderRequestDto.getItems().isEmpty()) {
            throw new InvalidOrderException("Order must have at least one item");
        }
        orderRequestDto.getItems().forEach(item -> {
            if (item.getProductId() == null || item.getProductId() <= 0) {
                throw new InvalidOrderException("Invalid product id");
            }
            if (item.getQuantity() <= 0) {
                throw new InvalidOrderException("Quantity must be greater than zero");
            }
        });

    }
}
