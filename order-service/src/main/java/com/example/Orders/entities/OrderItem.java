package com.example.Orders.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Table(name = "order_items")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItem {
 @Id
 @GeneratedValue(strategy= GenerationType.AUTO)
 private Long itemId;
 @ManyToOne
 @JoinColumn(name = "order_id")
 private Order order;

 @NotNull(message = "Product ID is required")
 @Column(name = "product_id")
<<<<<<< HEAD
 private Long productId;//modif
=======
 private Long productId;
>>>>>>> 9ce32bb9bce45bd806a0c090a908f0753cf01bc6
 @Column(name = "product_name")
 private String productName;
 @Positive(message = "Quantity must be positive")
 @Column(name = "quantity")
 private int quantity;
 @NotNull(message = "Unit price is required")
 @Column(name="unitPrice")
 private BigDecimal unitPrice;
}
