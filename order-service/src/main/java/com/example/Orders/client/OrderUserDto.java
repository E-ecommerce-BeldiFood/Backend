package com.example.Orders.client;

import com.example.Orders.dto.OrderResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderUserDto {
    private UserDTO userDTO;
    private OrderResponse orderResponse;
}
