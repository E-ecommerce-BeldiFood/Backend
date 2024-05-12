package com.example.Orders.dto;

import lombok.*;

import java.math.BigDecimal;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {
    private Long itemId;
    private Long productId;
    private String name;
    private BigDecimal price;
    private String imageUrl;
    private int quantity;

}
