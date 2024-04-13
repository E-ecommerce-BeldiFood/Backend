package com.example.Orders.repository;

import com.example.Orders.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByOrderItems_ProductNameContaining(String productName);

}
