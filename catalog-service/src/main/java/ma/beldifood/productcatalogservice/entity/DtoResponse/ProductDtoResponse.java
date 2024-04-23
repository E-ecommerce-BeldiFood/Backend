package ma.beldifood.productcatalogservice.entity.DtoResponse;

<<<<<<< HEAD
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductDtoResponse {
=======
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
>>>>>>> 9ce32bb9bce45bd806a0c090a908f0753cf01bc6
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
<<<<<<< HEAD
=======


>>>>>>> 9ce32bb9bce45bd806a0c090a908f0753cf01bc6
}
