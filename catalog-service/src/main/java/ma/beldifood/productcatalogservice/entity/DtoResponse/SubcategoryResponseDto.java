package ma.beldifood.productcatalogservice.entity.DtoResponse;

import lombok.Data;

@Data
public class SubcategoryResponseDto {

    private Long id;
    private String name;
    private Long categoryId;
    private String categoryName;
}