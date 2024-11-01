package ma.beldifood.productcatalogservice.controller;

import jakarta.transaction.Transactional;
import ma.beldifood.productcatalogservice.entity.DtoRequest.SubcategoryRequestDto;
import ma.beldifood.productcatalogservice.entity.DtoResponse.SubcategoryResponseDto;
import ma.beldifood.productcatalogservice.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Transactional
@RequestMapping("/subcategories")
<<<<<<< HEAD

=======
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
public class SubcategoryController {

    private final SubcategoryService subcategoryService;

    @Autowired
    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @GetMapping
    public ResponseEntity<List<SubcategoryResponseDto>> getAllSubcategories() {
        List<SubcategoryResponseDto> subcategories = subcategoryService.getAllSubcategories();
        return ResponseEntity.ok(subcategories);
    }

    @PostMapping
    public ResponseEntity<SubcategoryResponseDto> createSubcategory(@RequestBody SubcategoryRequestDto subcategoryRequestDto) {
        SubcategoryResponseDto createdSubcategory = subcategoryService.createSubcategory(subcategoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubcategory);
    }

    @GetMapping("/{subcategoryId}")
    public ResponseEntity<SubcategoryResponseDto> getSubcategoryById(@PathVariable Long subcategoryId) {
        SubcategoryResponseDto subcategory = subcategoryService.getSubcategoryById(subcategoryId);
        return ResponseEntity.ok(subcategory);
    }

    @PutMapping("/{subcategoryId}")
    public ResponseEntity<SubcategoryResponseDto> updateSubcategory(@PathVariable Long subcategoryId, @RequestBody SubcategoryRequestDto subcategoryRequestDto) {
        SubcategoryResponseDto updatedSubcategory = subcategoryService.updateSubcategory(subcategoryId, subcategoryRequestDto);
        return ResponseEntity.ok(updatedSubcategory);
    }

    @DeleteMapping("/{subcategoryId}")
    public ResponseEntity<Void> deleteSubcategory(@PathVariable Long subcategoryId) {
        subcategoryService.deleteSubcategory(subcategoryId);
        return ResponseEntity.noContent().build();
    }
}