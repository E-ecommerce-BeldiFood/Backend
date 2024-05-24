package com.example.Orders.dto;

import com.example.Orders.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderResponse {
    private Long orderId;
    private Long customerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    List<OrderItemResponse> orderItems=new ArrayList<>();
    private OrderStatus status;
    private Integer TotalQuantity;
    private BigDecimal totalPrice;
}
