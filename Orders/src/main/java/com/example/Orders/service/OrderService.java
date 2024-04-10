package com.example.Orders.service;

import com.example.Orders.dto.OrderRequestDto;
import com.example.Orders.dto.OrderResponseDto;

import java.util.List;

public interface OrderService {
    boolean isOrderIdExists(Long orderId);

    OrderResponseDto addOrder(OrderRequestDto orderRequestDto);

    OrderResponseDto getOrderById(Long orderId);

    List<OrderResponseDto> getAllOrders();

    OrderResponseDto updateOrder(Long orderId, OrderRequestDto orderRequestDto);

    void deleteOrder(Long orderId);

    // Stock Management Methods

}
