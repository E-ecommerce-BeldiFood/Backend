package com.example.Orders.dto;

import com.example.Orders.entities.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderRequestDto {
    private List<OrderItemDto> items;
    private OrderStatus status;
}
