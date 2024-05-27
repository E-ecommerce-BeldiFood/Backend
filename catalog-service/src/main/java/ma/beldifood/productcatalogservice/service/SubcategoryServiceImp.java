package ma.beldifood.productcatalogservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ma.beldifood.productcatalogservice.entity.Category;
import ma.beldifood.productcatalogservice.entity.DtoRequest.SubcategoryRequestDto;
import ma.beldifood.productcatalogservice.entity.DtoResponse.CategoryResponseDto;
import ma.beldifood.productcatalogservice.entity.DtoResponse.SubcategoryResponseDto;
import ma.beldifood.productcatalogservice.entity.Subcategory;
import ma.beldifood.productcatalogservice.repository.CategoryRepository;
import ma.beldifood.productcatalogservice.repository.SubcategoryRepository;
import ma.beldifood.productcatalogservice.utils.Mapping;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SubcategoryServiceImp implements  SubcategoryService{


    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    @Override
    public void massDeleteSubCategory(List<Long> id) {
        List<Subcategory> subcategoriesToDelete = subcategoryRepository.findAllById(id);
        subcategoryRepository.deleteAll(subcategoriesToDelete);
    }
    @Override
    public List<SubcategoryResponseDto> searchSubCategoryByName(String name) {
        List<Subcategory> subcategories = subcategoryRepository.findByNameContainingIgnoreCase(name);
        return subcategories.stream()
                .map(Mapping::mapToSubcategoryResponseDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<SubcategoryResponseDto> getAllSubcategories() {
        List<Subcategory> subcategories = subcategoryRepository.findAll();
        return subcategories.stream()
                .map(Mapping::mapToSubcategoryResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public SubcategoryResponseDto createSubcategory(SubcategoryRequestDto subcategoryRequestDto) {
        System.out.println(subcategoryRequestDto);
        Subcategory subcategory = Mapping.mapToSubcategory(subcategoryRequestDto);
        System.out.println(subcategory);
        Category category =categoryRepository.findById(subcategoryRequestDto.getCategoryId())
                .orElseThrow(() -> new NoSuchElementException("Category not found with id: " + subcategoryRequestDto.getCategoryId()));;
        subcategory.setCategory(category);
                subcategory = subcategoryRepository.save(subcategory);
        System.out.println(subcategory.getCategory());
        return Mapping.mapToSubcategoryResponseDto(subcategory);
    }

    @Override
    public SubcategoryResponseDto getSubcategoryById(Long id) {
        Subcategory subcategory= subcategoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Subcategory not found with id: " + id));
        return Mapping.mapToSubcategoryResponseDto(subcategory);
    }


    @Override
    public SubcategoryResponseDto updateSubcategory(Long id, SubcategoryRequestDto updatedSubcategory) {
        Subcategory existingSubcategory= subcategoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Subcategory not found with id: " + id));        existingSubcategory.setName(updatedSubcategory.getName());
        Category category = categoryRepository.findById(updatedSubcategory.getCategoryId())
                .orElseThrow(() -> new NoSuchElementException("Category not found with id: " + updatedSubcategory.getCategoryId()));

        existingSubcategory.setName(updatedSubcategory.getName());
        existingSubcategory.setCategory(category);
        Subcategory subcategory= subcategoryRepository.save(existingSubcategory);
        return  Mapping.mapToSubcategoryResponseDto(subcategory);
    }
    @Transactional
    @Override
    public void deleteSubcategory(Long id)
    {
        subcategoryRepository.deleteById(id);
    }
}