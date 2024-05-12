package com.example.Orders.Controller;

<<<<<<< HEAD
import com.example.Orders.dto.OrderRequest;
import com.example.Orders.dto.OrderResponse;
=======
import com.example.Orders.dto.OrderRequestDto;
import com.example.Orders.dto.OrderResponseDto;
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
import com.example.Orders.service.OrderService;
import jakarta.validation.Valid;
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


    @PostMapping
<<<<<<< HEAD
    public ResponseEntity<?> addOrder(@Valid @RequestBody OrderRequest orderRequestDto) {
        OrderResponse responseDto = orderService.addOrderWithItems(orderRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable long orderId) {
        OrderResponse orderResponse = orderService.getOrderById(orderId);
        return ResponseEntity.ok(orderResponse);
=======
    public ResponseEntity<OrderResponseDto> addOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto responseDto = orderService.addOrder(orderRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long orderId) {
        OrderResponseDto responseDto = orderService.getOrderById(orderId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        List<OrderResponseDto> responseDtoList = orderService.getAllOrders();
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> updateOrder(@PathVariable Long orderId, @Valid @RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto responseDto = orderService.updateOrder(orderId, orderRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
<<<<<<< HEAD

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




=======
    @GetMapping("/search")
    public ResponseEntity<List<OrderResponseDto>> getOrderByProductName(@RequestParam("productName") String productName) {
        List<OrderResponseDto> responseDtoList = orderService.getOrderByName(productName);
        return ResponseEntity.ok(responseDtoList);
    }

>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c

}
