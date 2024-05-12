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
import java.time.LocalDateTime;
import java.util.ArrayList;
=======
<<<<<<< HEAD
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
=======
import java.time.LocalDateTime;
>>>>>>> 9ce32bb9bce45bd806a0c090a908f0753cf01bc6
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
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

<<<<<<< HEAD
//    @NotNull(message = "Customer ID is required")
=======
    @NotNull(message = "Customer ID is required")
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name="created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
<<<<<<< HEAD

=======
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;
    @NotNull(message = "Total price is required")
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
    @Column(name="total_price")
    private BigDecimal totalPrice;
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
<<<<<<< HEAD
    private Integer TotalQuantity;

    @OneToMany(mappedBy = "order"/*,cascade = CascadeType.ALL, fetch = FetchType.LAZY*/)
    private List<OrderItems> orderItems= new ArrayList<>();
=======
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c


}
