package com.example.Orders.dto;

import com.example.Orders.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderRequest {
    private Long customerId;
    private Long orderId;
    private List<OrderItemRequest> orderItemsRequests = new ArrayList<>();
    private OrderStatus status;
}
