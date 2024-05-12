package com.example.Orders.service;

import com.example.Orders.dto.OrderRequest;
import com.example.Orders.dto.OrderResponse;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {

    public OrderResponse addOrderWithItems(@Valid OrderRequest orderRequest);

    OrderResponse getOrderById(long orderId);

    void deleteOrder(Long orderId);


    List<OrderResponse> getAllOrders();


    OrderResponse updateOrder(long orderId, @Valid OrderRequest orderRequest);
}
