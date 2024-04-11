package com.example.Orders.service;

import com.example.Orders.dto.OrderItemDto;
import com.example.Orders.dto.OrderRequestDto;
import com.example.Orders.dto.OrderResponseDto;
import com.example.Orders.entities.Order;
import com.example.Orders.exception.OrderNotFoundException;
import com.example.Orders.repository.OrderRepository;
import com.example.Orders.utils.Mapping;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImp implements OrderService{

    private final OrderRepository orderRepository;



    @Override
    public OrderResponseDto addOrder(@Valid OrderRequestDto orderRequestDto) {
        Order order = Mapping.mapToOrderEntity(orderRequestDto);
        Order savedOrder = orderRepository.save(order);
        return Mapping.mapToOrderResponseDto(savedOrder);
    }

    @Override
    public OrderResponseDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));
        return Mapping.mapToOrderResponseDto(order);
    }
    @Override
    public List<OrderResponseDto> getOrderByName(String productName){
        List<Order> orders = orderRepository.findByOrderItems_ProductNameContaining(productName);
        if (orders.isEmpty()) {
            throw new OrderNotFoundException("No orders found with product name: " + productName);
        }
        return orders.stream()
                .map(Mapping::mapToOrderResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(Mapping::mapToOrderResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDto updateOrder(Long orderId, @Valid OrderRequestDto orderRequestDto) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();
            Order updatedOrder = Mapping.mapToOrderEntity(orderRequestDto);
            updatedOrder.setOrderId(existingOrder.getOrderId());
            Order savedOrder = orderRepository.save(updatedOrder);
            return Mapping.mapToOrderResponseDto(savedOrder);
        } else {
            throw new OrderNotFoundException("Order not found with id: " + orderId);
        }
    }

    @Override
    public void deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new OrderNotFoundException("Order not found with id: " + orderId);
        }
        orderRepository.deleteById(orderId);
    }

}
