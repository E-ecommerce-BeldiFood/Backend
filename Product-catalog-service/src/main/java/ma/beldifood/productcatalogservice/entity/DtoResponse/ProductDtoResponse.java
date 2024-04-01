package ma.beldifood.productcatalogservice.entity.DtoResponse;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductDtoResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String imageUrl;
    private Long subcategoryId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
