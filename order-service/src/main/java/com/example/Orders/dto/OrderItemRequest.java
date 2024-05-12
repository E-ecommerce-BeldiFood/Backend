package com.example.Orders.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderItemRequest {
    private Long itemId;
    @NotNull(message = "Product ID is required")
    private Long productId;
    @Positive(message = "Quantity must be positive")
    private int quantity;

}
