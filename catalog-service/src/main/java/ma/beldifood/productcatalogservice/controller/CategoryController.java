package ma.beldifood.productcatalogservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ma.beldifood.productcatalogservice.entity.DtoRequest.CategoryRequestDto;
import ma.beldifood.productcatalogservice.entity.DtoRequest.ProductDtoRequest;
import ma.beldifood.productcatalogservice.entity.DtoResponse.CategoryResponseDto;
import ma.beldifood.productcatalogservice.entity.DtoResponse.ProductDtoResponse;
import ma.beldifood.productcatalogservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.logging.ErrorManager;


@RestController
@Transactional


@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final ObjectMapper objectMapper;
    private ErrorManager logger;



    @Autowired
    public CategoryController(CategoryService categoryService, ObjectMapper objectMapper) {
        this.categoryService = categoryService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
            List<CategoryResponseDto> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(categories);

    }

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestParam("category") String  categoryDtoRequestString,@RequestParam("categoryImage") MultipartFile categoryImage) throws JsonProcessingException {
        CategoryRequestDto categoryRequestDto = objectMapper.readValue(categoryDtoRequestString, CategoryRequestDto.class);
        CategoryResponseDto createdCategory = categoryService.createCategory(categoryRequestDto,categoryImage);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable Long categoryId) {
        CategoryResponseDto category = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto updatedCategory = categoryService.updateCategory(categoryId, categoryRequestDto);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }


}