package ma.beldifood.productcatalogservice.entity.DtoRequest;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDto {
    @NotBlank(message = "Name is required")
    private String name;
    private String imageUrl;
}
