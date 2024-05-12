package com.example.Orders.service;

<<<<<<< HEAD
import com.example.Orders.dto.OrderRequest;
import com.example.Orders.dto.OrderResponse;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
=======
import com.example.Orders.dto.OrderRequestDto;
import com.example.Orders.dto.OrderResponseDto;
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c

import java.util.List;

public interface OrderService {

<<<<<<< HEAD
    public OrderResponse addOrderWithItems(@Valid OrderRequest orderRequest);

    OrderResponse getOrderById(long orderId);
=======

    OrderResponseDto addOrder(OrderRequestDto orderRequestDto);

    OrderResponseDto getOrderById(Long orderId);
    List<OrderResponseDto> getOrderByName(String productName);

    List<OrderResponseDto> getAllOrders();

    OrderResponseDto updateOrder(Long orderId, OrderRequestDto orderRequestDto);
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c

    void deleteOrder(Long orderId);


<<<<<<< HEAD
    List<OrderResponse> getAllOrders();


    OrderResponse updateOrder(long orderId, @Valid OrderRequest orderRequest);
=======

>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
}
