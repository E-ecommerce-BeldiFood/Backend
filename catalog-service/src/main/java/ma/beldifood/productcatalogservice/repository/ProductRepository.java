package ma.beldifood.productcatalogservice.repository;

import ma.beldifood.productcatalogservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySubcategory_Category_CategoryId(Long categoryId);
    List<Product> findBySubcategory_SubcategoryId(Long subcategoryId);
}