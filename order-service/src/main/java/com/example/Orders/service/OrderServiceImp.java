package com.example.Orders.service;

<<<<<<< HEAD
import com.example.Orders.client.ProductDtoResponse;
import com.example.Orders.client.ProductServiceClient;
import com.example.Orders.dto.OrderItemRequest;
import com.example.Orders.dto.OrderRequest;
import com.example.Orders.dto.OrderResponse;
import com.example.Orders.entities.Order;
import com.example.Orders.entities.OrderItems;
import com.example.Orders.exception.OrderNotFoundException;
import com.example.Orders.repository.OrderItemRepository;
=======
<<<<<<< HEAD
=======
import com.example.Orders.component.RabbitMqExistenceProduct;
>>>>>>> 9ce32bb9bce45bd806a0c090a908f0753cf01bc6
import com.example.Orders.dto.OrderItemDto;
import com.example.Orders.dto.OrderRequestDto;
import com.example.Orders.dto.OrderResponseDto;
import com.example.Orders.entities.Order;
import com.example.Orders.entities.OrderItem;
import com.example.Orders.exception.OrderNotFoundException;
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
import com.example.Orders.repository.OrderRepository;
import com.example.Orders.utils.Mapping;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
import java.util.List;
=======
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
<<<<<<< HEAD
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
//    private RabbitMqExistenceProduct rabbitMqExistenceProduct;
    private OrderItemRepository orderItemRepository;
    private final ProductServiceClient productServiceClient;

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
}




=======
public class OrderServiceImp implements OrderService{

    private final OrderRepository orderRepository;
<<<<<<< HEAD
=======
    private RabbitMqExistenceProduct rabbitMqExistenceProduct;
>>>>>>> 9ce32bb9bce45bd806a0c090a908f0753cf01bc6




    @Transactional
    @Override
    public OrderResponseDto addOrder(@Valid OrderRequestDto orderRequestDto) {
        Order order = Mapping.mapToOrderEntity(orderRequestDto);
        List<OrderItemDto> orderItemDtos = orderRequestDto.getOrderItems();
        if (orderItemDtos != null) {
<<<<<<< HEAD
=======
            for (OrderItemDto itemDto : orderItemDtos){
                if (!(Boolean)rabbitMqExistenceProduct.checkProductExistence(itemDto.getProductId())) {
                    throw new OrderNotFoundException("Product not found with ID: " + itemDto.getProductId());
                }
            }
>>>>>>> 9ce32bb9bce45bd806a0c090a908f0753cf01bc6
            List<OrderItem> orderItems = orderItemDtos.stream()
                    .map(itemDto -> {
                        OrderItem orderItem = new OrderItem();
                        orderItem.setOrder(order);
                        orderItem.setProductId(itemDto.getProductId());
                        orderItem.setProductName(itemDto.getProductName());
                        orderItem.setQuantity(itemDto.getQuantity());
                        orderItem.setUnitPrice(itemDto.getUnitPrice());
                        return orderItem;
                    })
                    .collect(Collectors.toList());
            order.setOrderItems(orderItems);
        }
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
    @Transactional
    public OrderResponseDto updateOrder(Long orderId, @Valid OrderRequestDto orderRequestDto) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();
            Mapping.updateOrderEntity(orderRequestDto, existingOrder);
            Order savedOrder = orderRepository.save(existingOrder);
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
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
