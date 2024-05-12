package ma.beldifood.Orders.service;

import ma.beldifood.Orders.dto.OrderRequest;
import ma.beldifood.Orders.dto.OrderResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface OrderService {

    public OrderResponse addOrderWithItems(@Valid OrderRequest orderRequest);

    OrderResponse getOrderById(long orderId);

    void deleteOrder(Long orderId);


    List<OrderResponse> getAllOrders();


    OrderResponse updateOrder(long orderId, @Valid OrderRequest orderRequest);
}
