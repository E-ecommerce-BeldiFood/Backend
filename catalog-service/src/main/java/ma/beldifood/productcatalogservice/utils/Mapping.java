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

@NoArgsConstructor
public class Mapping {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static Product mapToProduct(ProductDtoRequest productDtoRequest) {
        Product product = modelMapper.map(productDtoRequest, Product.class);
        product.setId(null);
        return product;

    }

    public static ProductDtoResponse mapToProductResponseDto(Product product) {
        ProductDtoResponse productDtoResponse =modelMapper.map(product, ProductDtoResponse.class);
       // productDtoResponse.setSubcategoryName(product.getSubcategory().getName());

        return productDtoResponse;
    }


    public static Category mapToCategory(CategoryRequestDto categoryRequestDto) {
        return modelMapper.map(categoryRequestDto, Category.class);
    }

    public static CategoryResponseDto mapToCategoryResponseDto(Category category) {
        return modelMapper.map(category, CategoryResponseDto.class);
    }



    public static Subcategory mapToSubcategory(SubcategoryRequestDto subcategoryRequestDto) {
        Subcategory subcategory=  modelMapper.map(subcategoryRequestDto, Subcategory.class);
        subcategory.setId(null);
        return subcategory;
    }

    public static SubcategoryResponseDto mapToSubcategoryResponseDto(Subcategory subcategory) {
        return modelMapper.map(subcategory, SubcategoryResponseDto.class);
    }

}