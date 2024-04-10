package com.example.Orders.dto;

import com.example.Orders.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderRequestDto {
    private String customerId;
    private List<OrderItemDto> orderItems;
    private BigDecimal totalPrice;
    private OrderStatus status;
}
