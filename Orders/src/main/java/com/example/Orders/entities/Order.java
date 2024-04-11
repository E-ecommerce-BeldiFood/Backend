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
import java.util.Date;
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
    @CreationTimestamp
    @Column(name="created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;
    @NotNull(message = "Total price is required")
    @Column(name="total_price")
    private BigDecimal totalPrice;
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;


}
