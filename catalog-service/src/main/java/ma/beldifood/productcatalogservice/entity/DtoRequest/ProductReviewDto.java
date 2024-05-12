package ma.beldifood.productcatalogservice.entity.DtoRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReviewDto {

    private String name;
    private BigDecimal price;
    private String description;
    private String timeToPrepareInMinute;
    private boolean availability = true;
    private Long subcategoryId;
    private List<Review> reviews;
}
