package com.example.Orders.repository;

import com.example.Orders.entities.Order;
import com.example.Orders.entities.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItems,Long> {

    List<OrderItems> findByOrder(Order order);
}
