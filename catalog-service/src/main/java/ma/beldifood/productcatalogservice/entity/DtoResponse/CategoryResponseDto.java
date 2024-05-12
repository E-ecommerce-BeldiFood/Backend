package ma.beldifood.productcatalogservice.entity.DtoResponse;

import lombok.Data;

import java.util.List;

@Data
public class CategoryResponseDto {

    private Long id;
    private String name;
    private List<SubcategoryResponseDto> subcategories;
}