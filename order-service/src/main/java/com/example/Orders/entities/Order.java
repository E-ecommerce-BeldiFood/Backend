package com.example.Orders.entities;

import com.example.Orders.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
<<<<<<< HEAD
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
=======
import java.time.LocalDateTime;
>>>>>>> 9ce32bb9bce45bd806a0c090a908f0753cf01bc6
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

    @NotNull(message = "Customer ID is required")
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name="created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;
    @NotNull(message = "Total price is required")
    @Column(name="total_price")
    private BigDecimal totalPrice;
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;


}
