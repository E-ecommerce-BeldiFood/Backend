package ma.beldifood.productcatalogservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ma.beldifood.productcatalogservice.entity.Category;
import ma.beldifood.productcatalogservice.entity.DtoRequest.CategoryRequestDto;
import ma.beldifood.productcatalogservice.entity.DtoResponse.CategoryResponseDto;
import ma.beldifood.productcatalogservice.entity.DtoResponse.ProductDtoResponse;
import ma.beldifood.productcatalogservice.repository.CategoryRepository;
import ma.beldifood.productcatalogservice.repository.SubcategoryRepository;
import ma.beldifood.productcatalogservice.utils.Mapping;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;

    @Override
    public void massDeleteCategory(List<Long> id) {
        List<Category> categoriesToDelete = categoryRepository.findAllById(id);
        categoryRepository.deleteAll(categoriesToDelete);
    }
    @Override
    public List<CategoryResponseDto> searchCategoryByName(String name) {
        List<Category> categories = categoryRepository.findByNameContainingIgnoreCase(name);
        return categories.stream()
                .map(Mapping::mapToCategoryResponseDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<CategoryResponseDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(Mapping::mapToCategoryResponseDto)
                .toList();
    }

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
        Category category =Mapping.mapToCategory(categoryRequestDto);
        Category savedCategory = categoryRepository.save(category);
        return Mapping.mapToCategoryResponseDto(savedCategory);
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        Category category= categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category not found with id: " + id));
        return Mapping.mapToCategoryResponseDto(category);
    }


    @Override
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto updatedCategory) {
        Category existingCategory= categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category not found with id: " + id));

        existingCategory.setName(updatedCategory.getName());
        return Mapping.mapToCategoryResponseDto(categoryRepository.save(existingCategory));
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        // Delete subcategories associated with the category
        categoryRepository.findById(id).ifPresent(category -> {
            category.getSubcategories().forEach(subcategory -> subcategoryRepository.delete(subcategory));
        });
        // Then delete the category itself
        categoryRepository.deleteById(id);
    }

}