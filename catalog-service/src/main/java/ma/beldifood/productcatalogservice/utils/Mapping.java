package ma.beldifood.productcatalogservice.utils;

import lombok.NoArgsConstructor;
import ma.beldifood.productcatalogservice.entity.Category;
import ma.beldifood.productcatalogservice.entity.DtoRequest.CategoryRequestDto;
import ma.beldifood.productcatalogservice.entity.DtoRequest.ProductDtoRequest;
import ma.beldifood.productcatalogservice.entity.DtoRequest.SubcategoryRequestDto;
import ma.beldifood.productcatalogservice.entity.DtoResponse.CategoryResponseDto;
import ma.beldifood.productcatalogservice.entity.DtoResponse.ProductDtoResponse;
import ma.beldifood.productcatalogservice.entity.DtoResponse.SubcategoryResponseDto;
import ma.beldifood.productcatalogservice.entity.Product;
import ma.beldifood.productcatalogservice.entity.Subcategory;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class Mapping {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static Product mapToProduct(ProductDtoRequest productDtoRequest) {
        return modelMapper.map(productDtoRequest, Product.class);
    }

    public static ProductDtoResponse mapToProductResponseDto(Product product) {
        return modelMapper.map(product, ProductDtoResponse.class);
    }


    public static Category mapToCategory(CategoryRequestDto categoryRequestDto) {
        return modelMapper.map(categoryRequestDto, Category.class);
    }

    public static CategoryResponseDto mapToCategoryResponseDto(Category category) {
        //return modelMapper.map(category, CategoryResponseDto.class);

        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();

        // Map simple properties directly
        categoryResponseDto.setCategoryId(category.getCategoryId());
        categoryResponseDto.setName(category.getName());

        // Map subcategories if they exist
        if (category.getSubcategories() != null) {
            List<SubcategoryResponseDto> subcategoryResponseDtos = category.getSubcategories().stream()
                    .map(subcategory -> Mapping.mapToSubcategoryResponseDto(subcategory))
                    .collect(Collectors.toList());
            categoryResponseDto.setSubcategories(subcategoryResponseDtos);
        }

        return categoryResponseDto;
    }



    public static Subcategory mapToSubcategory(SubcategoryRequestDto subcategoryRequestDto) {
        return modelMapper.map(subcategoryRequestDto, Subcategory.class);
    }

    public static SubcategoryResponseDto mapToSubcategoryResponseDto(Subcategory subcategory) {
        return modelMapper.map(subcategory, SubcategoryResponseDto.class);
    }

}