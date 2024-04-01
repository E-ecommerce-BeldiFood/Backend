package com.example.Orders.service;

import com.example.Orders.dto.OrderItemDto;
import com.example.Orders.dto.OrderRequestDto;
import com.example.Orders.dto.OrderResponseDto;
import com.example.Orders.entities.Order;
import com.example.Orders.entities.OrderItem;
import com.example.Orders.exception.OrderNotFoundException;
import com.example.Orders.repository.OrderRepository;
import com.example.Orders.utils.Mapping;
import com.example.Orders.utils.OrderInputValidation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImp implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderInputValidation orderInputValidation;


    @Override
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        orderInputValidation.validateOrderRequest(orderRequestDto);
        Order order = Mapping.mapToOrderEntity(orderRequestDto);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        return Mapping.mapToOrderResponseDto(savedOrder);
    }

    @Override
    public OrderResponseDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
        return Mapping.mapToOrderResponseDto(order);
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(Mapping::mapToOrderResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDto updateOrder(Long id, OrderRequestDto orderRequestDto) {
        orderInputValidation.validateOrderRequest(orderRequestDto);
        Order existingOrder = getOrderEntityById(id);
        Order updatedOrder = Mapping.mapToOrderEntity(orderRequestDto);
        updatedOrder.setId(existingOrder.getId());
        updatedOrder.setCreatedAt(existingOrder.getCreatedAt());
        updatedOrder.setUpdatedAt(LocalDateTime.now());
        updatedOrder.setItems(existingOrder.getItems());
        Order savedOrder = orderRepository.save(updatedOrder);
        return Mapping.mapToOrderResponseDto(savedOrder);
    }
    private Order getOrderEntityById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
    }
    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }
}
