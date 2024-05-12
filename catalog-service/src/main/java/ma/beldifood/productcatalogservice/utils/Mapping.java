package ma.beldifood.productcatalogservice.utils;

import lombok.NoArgsConstructor;
import ma.beldifood.productcatalogservice.entity.Category;
<<<<<<< HEAD
import ma.beldifood.productcatalogservice.entity.DtoRequest.*;
=======
import ma.beldifood.productcatalogservice.entity.DtoRequest.CategoryRequestDto;
import ma.beldifood.productcatalogservice.entity.DtoRequest.ProductDtoRequest;
import ma.beldifood.productcatalogservice.entity.DtoRequest.SubcategoryRequestDto;
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
import ma.beldifood.productcatalogservice.entity.DtoResponse.CategoryResponseDto;
import ma.beldifood.productcatalogservice.entity.DtoResponse.ProductDtoResponse;
import ma.beldifood.productcatalogservice.entity.DtoResponse.SubcategoryResponseDto;
import ma.beldifood.productcatalogservice.entity.Product;
import ma.beldifood.productcatalogservice.entity.Subcategory;
import org.modelmapper.ModelMapper;
<<<<<<< HEAD
import org.springframework.beans.BeanUtils;

import java.util.List;
=======
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c

@NoArgsConstructor
public class Mapping {
    private static final ModelMapper modelMapper = new ModelMapper();

<<<<<<< HEAD
    public static ProductReviewDto mapToProductReviewDto(Product product, List<Review>reviews){
        ProductReviewDto productReviewDto = new ProductReviewDto();
        BeanUtils.copyProperties(product, productReviewDto);
        productReviewDto.setReviews(reviews);
        return productReviewDto;
    }

=======
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
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