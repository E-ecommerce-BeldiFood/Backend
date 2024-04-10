package com.example.Orders.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

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

 @NotNull
 @Column(name = "product_id")
 private Long productId;//modif
 @Column(name = "product_name")
 private String productName;
 @Column(name = "quantity")
 private int quantity;
 @NotNull
 @Column(name="price")
 private BigDecimal price;
}
