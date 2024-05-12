package com.example.Orders.repository;

import com.example.Orders.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface OrderRepository extends JpaRepository<Order,Long> {
<<<<<<< HEAD

=======
    List<Order> findByOrderItems_ProductNameContaining(String productName);
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c

}
