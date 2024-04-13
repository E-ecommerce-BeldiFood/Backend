package ma.beldifood.productcatalogservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal price;

    private String description;

    @NotBlank(message = "Image URL is required")
    private String imageUrl;

    @NotNull(message = "time is required")
    private double timeToPrepareInMinute;


    private boolean availability = true;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


    private int positiveFeedback=0;

    private int negativeFeedback=0;

    @ManyToOne
    //to ignore it when we gonna show procut
    @ToString.Exclude
    private Subcategory subcategory;

}