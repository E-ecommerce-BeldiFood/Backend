package ma.beldifood.Orders.entities;

import ma.beldifood.Orders.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "orders")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

//    @NotNull(message = "Customer ID is required")
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name="created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name="total_price")
    private BigDecimal totalPrice;
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private Integer TotalQuantity;

    @OneToMany(mappedBy = "order"/*,cascade = CascadeType.ALL, fetch = FetchType.LAZY*/)
    private List<OrderItems> orderItems= new ArrayList<>();


}
