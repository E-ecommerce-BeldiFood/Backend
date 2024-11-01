package com.example.Orders.dto;

import com.example.Orders.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderRequestDto {
    @NotNull(message = "Customer ID is required")
    private Long customerId;
<<<<<<< HEAD
=======
    private Long orderId;
>>>>>>> 9ce32bb9bce45bd806a0c090a908f0753cf01bc6
    private List<OrderItemDto> orderItems;
    @NotNull(message = "Total price is required")
    private BigDecimal totalPrice;
    private OrderStatus status;
}
