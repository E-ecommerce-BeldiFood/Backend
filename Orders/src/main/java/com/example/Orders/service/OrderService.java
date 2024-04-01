package com.example.Orders.service;

import com.example.Orders.dto.OrderRequestDto;
import com.example.Orders.dto.OrderResponseDto;

import java.util.List;

public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);
    OrderResponseDto getOrderById(Long id);
    List<OrderResponseDto> getAllOrders();
    OrderResponseDto updateOrder(Long id, OrderRequestDto orderRequestDto);
    void deleteOrder(Long id);
}
