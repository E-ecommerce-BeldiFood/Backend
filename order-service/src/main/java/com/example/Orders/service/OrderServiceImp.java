package com.example.Orders.service;

import com.example.Orders.client.*;
import com.example.Orders.dto.OrderItemRequest;
import com.example.Orders.dto.OrderRequest;
import com.example.Orders.dto.OrderResponse;
import com.example.Orders.entities.Order;
import com.example.Orders.entities.OrderItems;
import com.example.Orders.enums.OrderStatus;
import com.example.Orders.exception.OrderNotFoundException;
import com.example.Orders.repository.OrderItemRepository;
import com.example.Orders.repository.OrderRepository;
import com.example.Orders.utils.Mapping;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
//    private RabbitMqExistenceProduct rabbitMqExistenceProduct;
    private OrderItemRepository orderItemRepository;
    private final ProductServiceClient productServiceClient;
    private final UserServiceClient userServiceClient;


@Override
public List<OrderUserDto> findAllUserOrders() {
    List<OrderUserDto> orderUserDtoList = new ArrayList<>();
    List<Order> orders = orderRepository.findAll();

    for (Order order : orders) {
        OrderUserDto orderUserDto = new OrderUserDto();

        // Fetch user details
        UserDTO userDTO = userServiceClient.getUserById(order.getCustomerId()).getBody();

        // Map order to order response
        OrderResponse orderResponse = Mapping.mapToResponse(order);
        orderResponse.setOrderItems(Mapping.mapToOrderItemResponseList(order.getOrderItems()));

        // Set user and order details in OrderUserDto
        orderUserDto.setUserDTO(userDTO);
        orderUserDto.setOrderResponse(orderResponse);

        // Add to the result list
        orderUserDtoList.add(orderUserDto);
    }

    return orderUserDtoList;
}


    @Override
    @Transactional
    public OrderResponse addOrderWithItems(@Valid OrderRequest orderRequest) {
        Order order = Mapping.mapToEntity(orderRequest);
        Order savedOrder = orderRepository.save(order);
        OrderResponse orderResponse = Mapping.mapToResponse(savedOrder);
        List<OrderItemRequest> orderItemRequests = orderRequest.getOrderItemsRequests();
        if (orderItemRequests != null && !orderItemRequests.isEmpty()) {
            String orderId = String.valueOf(savedOrder.getOrderId());
            List<OrderItems> savedOrderItems = orderItemRequests.stream()
                    .map(itemDto -> {
                        ProductDtoResponse productDto = productServiceClient.getProductById(itemDto.getProductId());
                        if (productDto == null) {
                            throw new OrderNotFoundException("Product not found with ID: " + itemDto.getProductId());
                        }
                        OrderItems orderItem = Mapping.mapToOrderItems(itemDto, productDto, orderId);
                        orderItem.setOrder(savedOrder);
                        return orderItemRepository.save(orderItem);
                    })
                    .collect(Collectors.toList());
            savedOrder.setOrderItems(savedOrderItems);

        }
        // Return the response using the updated order
        return Mapping.mapToResponse(savedOrder);
    }


    @Override
    public OrderResponse getOrderById(long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));
        // Explicitly fetch order items
        order.getOrderItems().size(); // This triggers the lazy loading of order items

        OrderResponse orderResponse = Mapping.mapToResponse(order);
        orderResponse.setOrderItems(Mapping.mapToOrderItemResponseList(order.getOrderItems()));
        return orderResponse;
    }



    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        orders.forEach(order -> order.getOrderItems().size()); // Explicitly fetch order items for each order

        return orders.stream()
                .map(this::mapToResponseWithItems)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));

        // Disassociate OrderItems from the Order entity
        order.getOrderItems().forEach(orderItem -> orderItem.setOrder(null));

        // Save the changes to disassociate OrderItems
        orderRepository.save(order);

        // Now you can safely delete the Order entity
        orderRepository.delete(order);
    }

    @Transactional
    @Override
    public OrderResponse updateOrder(long orderId, @Valid OrderRequest orderRequest) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));

        // Update existing order fields
        existingOrder.setCustomerId(orderRequest.getCustomerId()); // Update customer ID or other fields as needed

        // Update order items
        List<OrderItemRequest> orderItemRequests = orderRequest.getOrderItemsRequests();
        if (orderItemRequests != null && !orderItemRequests.isEmpty()) {
            String orderIdStr = String.valueOf(existingOrder.getOrderId());
            List<OrderItems> updatedOrderItems = orderItemRequests.stream()
                    .map(itemDto -> {
                        ProductDtoResponse productDto = productServiceClient.getProductById(itemDto.getProductId());
                        if (productDto == null) {
                            throw new OrderNotFoundException("Product not found with ID: " + itemDto.getProductId());
                        }
                        OrderItems orderItem = Mapping.mapToOrderItems(itemDto, productDto, orderIdStr);
                        return orderItemRepository.save(orderItem);
                    })
                    .collect(Collectors.toList());
            existingOrder.setOrderItems(updatedOrderItems);
        }

        // Save the updated order
        Order updatedOrder = orderRepository.save(existingOrder);
        return mapToResponseWithItems(updatedOrder);
    }

    private OrderResponse mapToResponseWithItems(Order order) {
        OrderResponse orderResponse = Mapping.mapToResponse(order);
        orderResponse.setOrderItems(Mapping.mapToOrderItemResponseList(order.getOrderItems()));

        return orderResponse;
    }

    public void updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found with id: " + orderId));

        order.setStatus(newStatus);
        orderRepository.save(order);
    }

}




