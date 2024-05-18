package ma.beldifood.Orders.repository;

import ma.beldifood.Orders.entities.Order;
import ma.beldifood.Orders.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

    List<OrderItem> findByOrder(Order order);
}
