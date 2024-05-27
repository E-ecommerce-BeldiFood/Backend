package ma.beldifood.productcatalogservice.repository;

import ma.beldifood.productcatalogservice.entity.Category;
import ma.beldifood.productcatalogservice.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    public Subcategory findByName(String name);
    List<Subcategory> findByNameContainingIgnoreCase(String name);
}