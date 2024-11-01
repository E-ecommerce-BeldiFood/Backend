package com.example.Orders.utils;

<<<<<<< HEAD
import com.example.Orders.client.ProductDtoResponse;
import com.example.Orders.dto.OrderItemRequest;
import com.example.Orders.dto.OrderItemResponse;
import com.example.Orders.dto.OrderRequest;
import com.example.Orders.dto.OrderResponse;
import com.example.Orders.entities.Order;
import com.example.Orders.entities.OrderItems;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
=======
import com.example.Orders.dto.OrderItemDto;
import com.example.Orders.dto.OrderRequestDto;
import com.example.Orders.dto.OrderResponseDto;
import com.example.Orders.entities.Order;
import com.example.Orders.entities.OrderItem;
import org.modelmapper.ModelMapper;


import java.time.LocalDateTime;
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
import java.util.List;
import java.util.stream.Collectors;

public class Mapping {
    private static final ModelMapper modelMapper = new ModelMapper();
<<<<<<< HEAD

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.typeMap(OrderRequest.class, Order.class)
                .addMappings(mapper -> mapper.skip(Order::setOrderItems));
        modelMapper.typeMap(OrderItemRequest.class, OrderItems.class)
                .addMappings(mapper -> mapper.skip(OrderItems::setOrder));
    }


    public static Order mapToEntity(OrderRequest orderRequest){return modelMapper.map(orderRequest, Order.class);}

    public static OrderResponse mapToResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(order.getOrderId());
        orderResponse.setCreatedAt(LocalDateTime.now());
        orderResponse.setUpdatedAt(LocalDateTime.now());
        orderResponse.setTotalPrice(calculateTotalPrice(order.getOrderItems()));
        orderResponse.setCustomerId(order.getCustomerId());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setTotalQuantity(calculateTotalQuantity(order.getOrderItems()));
        orderResponse.setOrderItems(mapToOrderItemResponseList(order.getOrderItems()));
        return orderResponse;
    }
    private static Integer calculateTotalQuantity(List<OrderItems> orderItems){
        return orderItems.stream()
                .mapToInt(OrderItems::getQuantity)
                .sum();
    }
    private static BigDecimal calculateTotalPrice(List<OrderItems> orderItems) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderItems item : orderItems) {
            totalPrice = totalPrice.add(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        return totalPrice;
    }
    public static OrderItems mapToOrderItems(OrderItemRequest itemDto, ProductDtoResponse productDto, String orderId) {
        OrderItems orderItem = new OrderItems();
        orderItem.setItemId(itemDto.getItemId());
        orderItem.setProductId(itemDto.getProductId());
        orderItem.setName(productDto.getName());
        orderItem.setPrice(productDto.getPrice());
        orderItem.setImageUrl(productDto.getImageUrl());
        orderItem.setQuantity(itemDto.getQuantity());
        return orderItem;
    }

    public static OrderItemResponse mapToOrderItemResponse(OrderItems orderItem) {
        OrderItemResponse orderItemResponse = new OrderItemResponse();
        orderItemResponse.setItemId(orderItem.getItemId());
        orderItemResponse.setProductId(orderItem.getProductId());
        orderItemResponse.setName(orderItem.getName());
        orderItemResponse.setPrice(orderItem.getPrice());
        orderItemResponse.setImageUrl(orderItem.getImageUrl());
        orderItemResponse.setQuantity(orderItem.getQuantity());
        return orderItemResponse;
    }
    public static List<OrderItemResponse> mapToOrderItemResponseList(List<OrderItems> orderItems) {
        return orderItems.stream()
                .map(Mapping::mapToOrderItemResponse)
                .collect(Collectors.toList());
    }


=======
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
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
}
