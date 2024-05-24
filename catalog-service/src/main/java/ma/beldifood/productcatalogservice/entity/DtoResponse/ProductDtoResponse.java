package ma.beldifood.productcatalogservice.entity.DtoResponse;

import io.micrometer.observation.ObservationFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.beldifood.productcatalogservice.entity.Status;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;


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
    private String timeToPrepareInMinute;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}
