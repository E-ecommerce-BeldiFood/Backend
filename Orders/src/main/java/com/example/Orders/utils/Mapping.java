package com.example.Orders.utils;

import com.example.Orders.dto.OrderItemDto;
import com.example.Orders.dto.OrderRequestDto;
import com.example.Orders.dto.OrderResponseDto;
import com.example.Orders.entities.Order;
import com.example.Orders.entities.OrderItem;
import org.modelmapper.ModelMapper;

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
        OrderResponseDto orderResponseDto = modelMapper.map(order, OrderResponseDto.class);
        orderResponseDto.setOrderItems(order.getOrderItems().stream()
                .map(Mapping::mapToOrderItemDto)
                .collect(Collectors.toList()));
        return orderResponseDto;
    }

    private static OrderItemDto mapToOrderItemDto(OrderItem orderItem) {
        return modelMapper.map(orderItem, OrderItemDto.class);
    }
}
