package ma.beldifood.productcatalogservice.entity.DtoResponse;

import lombok.Data;

@Data
public class SubcategoryResponseDto {

    private Long subcategoryId;
    private String name;
    private Long categoryId;
    private Long categoryName;
}