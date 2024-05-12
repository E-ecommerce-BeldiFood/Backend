package ma.beldifood.Orders.Controller;

import ma.beldifood.Orders.dto.OrderRequest;
import ma.beldifood.Orders.dto.OrderResponse;
import ma.beldifood.Orders.service.OrderService;
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





}
