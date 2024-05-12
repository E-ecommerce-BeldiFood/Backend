package ma.beldifood.Orders.entities;

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
public class OrderItems  {
 @Id
 @GeneratedValue(strategy= GenerationType.AUTO)
 private Long itemId;

 @NotNull(message = "Product ID is required")
 @Column(name = "product_id")
 private Long productId;

 private String name;
 private BigDecimal price;
 private String imageUrl;
 @Positive(message = "Quantity must be positive")
 @Column(name = "quantity")
 private int quantity;

 @ManyToOne
 private Order order;
}
