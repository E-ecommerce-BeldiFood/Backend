package ma.beldifood.Orders.dto;

import ma.beldifood.Orders.enums.OrderStatus;
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
