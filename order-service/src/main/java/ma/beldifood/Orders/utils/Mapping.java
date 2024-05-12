package ma.beldifood.Orders.utils;

import ma.beldifood.Orders.client.ProductDtoResponse;
import ma.beldifood.Orders.dto.OrderItemRequest;
import ma.beldifood.Orders.dto.OrderItemResponse;
import ma.beldifood.Orders.dto.OrderRequest;
import ma.beldifood.Orders.dto.OrderResponse;
import ma.beldifood.Orders.entities.Order;
import ma.beldifood.Orders.entities.OrderItems;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Mapping {
    private static final ModelMapper modelMapper = new ModelMapper();

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


}