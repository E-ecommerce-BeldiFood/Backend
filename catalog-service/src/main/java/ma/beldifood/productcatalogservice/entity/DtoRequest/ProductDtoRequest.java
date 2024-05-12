package ma.beldifood.productcatalogservice.entity.DtoRequest;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDtoRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal price;

    private String description;


    @NotNull(message = "time is required")
<<<<<<< HEAD
    private String timeToPrepareInMinute;
=======
    private double timeToPrepareInMinute;
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c

    private boolean availability = true;

    @NotNull(message = "Subcategory ID is required")
    @NotNull
    private Long subcategoryId;

<<<<<<< HEAD

=======
<<<<<<< HEAD
=======

>>>>>>> 9ce32bb9bce45bd806a0c090a908f0753cf01bc6
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
}