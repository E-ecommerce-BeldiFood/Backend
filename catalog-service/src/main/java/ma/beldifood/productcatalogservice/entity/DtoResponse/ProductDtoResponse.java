package ma.beldifood.productcatalogservice.entity.DtoResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDtoResponse  {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String imageUrl;
    private String subcategoryName;
    private double timeToPrepareInMinute;
    private boolean availability ;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
