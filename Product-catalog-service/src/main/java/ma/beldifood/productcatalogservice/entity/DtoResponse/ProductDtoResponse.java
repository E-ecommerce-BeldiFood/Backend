package ma.beldifood.productcatalogservice.entity.DtoResponse;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductDtoResponse {
    private Long productId;
    private String name;
    private BigDecimal price;
    private String description;
    private String imageUrl;
    private String subcategory_name;
    private double timeToPrepareInMinute;
    private boolean availability ;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
