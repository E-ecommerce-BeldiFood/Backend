package ma.beldifood.productcatalogservice.service;

import ma.beldifood.productcatalogservice.entity.DtoRequest.CategoryRequestDto;
import ma.beldifood.productcatalogservice.entity.DtoResponse.CategoryResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> getAllCategories();
    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto, MultipartFile file);
    CategoryResponseDto getCategoryById(Long categoryId);
    CategoryResponseDto updateCategory(Long categoryId, CategoryRequestDto categoryRequestDto);
    void deleteCategory(Long categoryId);
}