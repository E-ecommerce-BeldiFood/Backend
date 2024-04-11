package com.example.Orders.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderItemDto {
    @NotNull(message = "Product ID is required")
    private Long productId;
    private String productName;
    private int quantity;
    @NotNull(message = "Unit price is required")
    private BigDecimal unitPrice;
}
