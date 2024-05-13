package ma.beldifood.productcatalogservice.entity.DtoResponse;

import io.micrometer.observation.ObservationFilter;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import ma.beldifood.productcatalogservice.entity.enums.STATUS;
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
    private STATUS status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}
