package ma.beldifood.productcatalogservice.service;

import ma.beldifood.productcatalogservice.entity.DtoRequest.SubcategoryRequestDto;
import ma.beldifood.productcatalogservice.entity.DtoResponse.CategoryResponseDto;
import ma.beldifood.productcatalogservice.entity.DtoResponse.SubcategoryResponseDto;

import java.util.List;

public interface SubcategoryService {
    List<SubcategoryResponseDto> getAllSubcategories();
    SubcategoryResponseDto createSubcategory(SubcategoryRequestDto subcategoryRequestDto);
    SubcategoryResponseDto getSubcategoryById(Long subcategoryId);
    SubcategoryResponseDto updateSubcategory(Long subcategoryId, SubcategoryRequestDto subcategoryRequestDto);
    void deleteSubcategory(Long subcategoryId);

    void massDeleteSubCategory(List<Long> id);
    List<SubcategoryResponseDto> searchSubCategoryByName(String name);
}