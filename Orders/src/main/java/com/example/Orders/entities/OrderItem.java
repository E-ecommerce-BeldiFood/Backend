package com.example.Orders.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
 private Long productId;//modif
 @Column(name = "product_name")
 private String productName;
 @Column(name = "quantity")
 private int quantity;
 @NotNull(message = "Unit price is required")
 @Column(name="unitPrice")
 private BigDecimal unitPrice;
}
