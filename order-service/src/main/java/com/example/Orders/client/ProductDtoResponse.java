package com.example.Orders.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDtoResponse {
    private String name;
    private BigDecimal price;
    private String imageUrl;
}
