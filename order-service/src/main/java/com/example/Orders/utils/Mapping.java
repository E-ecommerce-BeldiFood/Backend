package com.example.Orders.utils;

import com.example.Orders.dto.OrderItemDto;
import com.example.Orders.dto.OrderRequestDto;
import com.example.Orders.dto.OrderResponseDto;
import com.example.Orders.entities.Order;
import com.example.Orders.entities.OrderItem;
import org.modelmapper.ModelMapper;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Mapping {
    private static final ModelMapper modelMapper = new ModelMapper();
    public static Order mapToOrderEntity(OrderRequestDto orderRequestDto) {
        Order order = modelMapper.map(orderRequestDto, Order.class);
        order.setOrderItems(orderRequestDto.getOrderItems().stream()
                .map(Mapping::mapToOrderItemEntity)
                .collect(Collectors.toList()));
        return order;
    }
    public static OrderItem mapToOrderItemEntity(OrderItemDto orderItemDto) {
        return modelMapper.map(orderItemDto, OrderItem.class);
    }
    public static OrderResponseDto mapToOrderResponseDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId(order.getOrderId());
        orderResponseDto.setCustomerId(order.getCustomerId());
        orderResponseDto.setCreatedAt(LocalDateTime.now());
        orderResponseDto.setUpdatedAt(LocalDateTime.now());
        orderResponseDto.setTotalPrice(order.getTotalPrice());
        orderResponseDto.setStatus(order.getStatus());
        orderResponseDto.setOrderItems(order.getOrderItems().stream()
                .map(orderItem -> mapToOrderItemDto(orderItem))
                .collect(Collectors.toList()));
        orderResponseDto.setQuantity(calculateTotalQuantity(order.getOrderItems()));
        return orderResponseDto;
    }
    private static Integer calculateTotalQuantity(List<OrderItem> orderItems) {
        return orderItems.stream()
                .mapToInt(OrderItem::getQuantity)
                .sum();
    }

    private static OrderItemDto mapToOrderItemDto(OrderItem orderItem) {
        return modelMapper.map(orderItem, OrderItemDto.class);
    }
    public static void updateOrderEntity(OrderRequestDto orderRequestDto, Order existingOrder) {
        existingOrder.setCustomerId(orderRequestDto.getCustomerId());
        existingOrder.setTotalPrice(orderRequestDto.getTotalPrice());
        existingOrder.setStatus(orderRequestDto.getStatus());
        existingOrder.getOrderItems().clear();

        orderRequestDto.getOrderItems().forEach(orderItemDto -> {
            OrderItem orderItem = mapToOrderItemEntity(orderItemDto);
            orderItem.setOrder(existingOrder);
            existingOrder.getOrderItems().add(orderItem);
        });
    }
}
