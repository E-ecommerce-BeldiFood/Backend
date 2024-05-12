package ma.beldifood.Orders.repository;

import ma.beldifood.Orders.entities.Order;
import ma.beldifood.Orders.entities.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItems,Long> {

    List<OrderItems> findByOrder(Order order);
}
