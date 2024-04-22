package ma.beldifood.productcatalogservice.entity.DtoRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubcategoryRequestDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "categoryId is required")

    private Long categoryId;
}