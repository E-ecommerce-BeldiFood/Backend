package ma.beldifood.productcatalogservice.service;

import ma.beldifood.productcatalogservice.entity.Subcategory;
import ma.beldifood.productcatalogservice.repository.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SubcategoryServiceImp implements  SubcategoryService{

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    public List<Subcategory> getAllSubcategories() {
        return subcategoryRepository.findAll();
    }

    public Subcategory getSubcategoryById(Long id) {
        return subcategoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Subcategory not found with id: " + id));
    }

    public Subcategory createSubcategory(Subcategory subcategory) {
        return subcategoryRepository.save(subcategory);
    }

    public Subcategory updateSubcategory(Long id, Subcategory updatedSubcategory) {
        Subcategory existingSubcategory = getSubcategoryById(id);
        existingSubcategory.setName(updatedSubcategory.getName());
        existingSubcategory.setCategory(updatedSubcategory.getCategory());
        return subcategoryRepository.save(existingSubcategory);
    }

    public void deleteSubcategory(Long id) {
        subcategoryRepository.deleteById(id);
    }
}