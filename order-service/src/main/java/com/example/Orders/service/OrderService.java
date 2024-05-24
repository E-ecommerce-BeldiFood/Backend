package com.example.Orders.service;

import com.example.Orders.client.OrderUserDto;
import com.example.Orders.dto.OrderRequest;
import com.example.Orders.dto.OrderResponse;
import com.example.Orders.enums.OrderStatus;
import jakarta.validation.Valid;

import java.util.List;

public interface OrderService {

    List<OrderUserDto> findAllUserOrders();
    public OrderResponse addOrderWithItems(@Valid OrderRequest orderRequest);

    OrderResponse getOrderById(long orderId);

    void deleteOrder(Long orderId);


    List<OrderResponse> getAllOrders();


    OrderResponse updateOrder(long orderId, @Valid OrderRequest orderRequest);

    void updateOrderStatus(Long orderId, OrderStatus newStatus);
}
