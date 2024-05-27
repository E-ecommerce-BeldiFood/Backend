package ma.beldifood.productcatalogservice.service;

import ma.beldifood.productcatalogservice.entity.DtoRequest.CategoryRequestDto;
import ma.beldifood.productcatalogservice.entity.DtoResponse.CategoryResponseDto;
import ma.beldifood.productcatalogservice.entity.DtoResponse.ProductDtoResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> getAllCategories();
    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto getCategoryById(Long categoryId);
    CategoryResponseDto updateCategory(Long categoryId, CategoryRequestDto categoryRequestDto);
    void deleteCategory(Long categoryId);

    void massDeleteCategory(List<Long> id);
    List<CategoryResponseDto> searchCategoryByName(String name);
}