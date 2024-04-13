package ma.beldifood.productcatalogservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subcategoryId;

    @NotBlank
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

}
