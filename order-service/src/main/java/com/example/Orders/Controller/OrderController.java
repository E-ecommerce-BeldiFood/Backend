package com.example.Orders.Controller;

import com.example.Orders.client.OrderUserDto;
import com.example.Orders.dto.OrderRequest;
import com.example.Orders.dto.OrderResponse;
import com.example.Orders.enums.OrderStatus;
import com.example.Orders.service.OrderService;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @GetMapping("/allOrders")
    private ResponseEntity<List<OrderUserDto>> handleGetOrdersUsers() {
        List<OrderUserDto> orderUserDtoList = orderService.findAllUserOrders();
        return ResponseEntity.ok(orderUserDtoList);
    }


    @PostMapping
    public ResponseEntity<?> addOrder(@Valid @RequestBody OrderRequest orderRequestDto) {
        OrderResponse responseDto = orderService.addOrderWithItems(orderRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable long orderId) {
        OrderResponse orderResponse = orderService.getOrderById(orderId);
        return ResponseEntity.ok(orderResponse);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> orderResponses = orderService.getAllOrders();
        return ResponseEntity.ok(orderResponses);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable long orderId, @Valid @RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.updateOrder(orderId, orderRequest);
        return ResponseEntity.ok(orderResponse);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus newStatus) {
        try {
            orderService.updateOrderStatus(id, newStatus);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update order status", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }




}
