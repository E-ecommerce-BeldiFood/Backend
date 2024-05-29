package ma.beldifood.productcatalogservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

import java.util.List;


@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    @NotBlank(message = "Image URL is required")
    private String imageUrl;
    @ToString.Exclude
    @OneToMany(mappedBy = "category")
    private List<Subcategory> subcategories;
}