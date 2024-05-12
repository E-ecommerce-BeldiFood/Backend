package ma.beldifood.productcatalogservice.entity.DtoResponse;

<<<<<<< HEAD
import io.micrometer.observation.ObservationFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
=======
<<<<<<< HEAD
import lombok.Data;
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c

import java.math.BigDecimal;
import java.time.LocalDateTime;

<<<<<<< HEAD
=======
@Data
public class ProductDtoResponse {
=======
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDtoResponse  {
<<<<<<< HEAD
=======
>>>>>>> 9ce32bb9bce45bd806a0c090a908f0753cf01bc6
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String imageUrl;
    private String subcategoryName;
<<<<<<< HEAD
    private String timeToPrepareInMinute;
    private boolean availability ;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



=======
    private double timeToPrepareInMinute;
    private boolean availability ;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
<<<<<<< HEAD
=======


>>>>>>> 9ce32bb9bce45bd806a0c090a908f0753cf01bc6
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
}
