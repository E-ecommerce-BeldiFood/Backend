package ma.beldifood.productcatalogservice.repository;

import ma.beldifood.productcatalogservice.entity.Product;
import ma.beldifood.productcatalogservice.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
=======
import org.springframework.scheduling.config.Task;
>>>>>>> 9ce32bb9bce45bd806a0c090a908f0753cf01bc6
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySubcategory_Category_Id(Long categoryId);
    List<Product> findBySubcategory_Id(Long subcategoryId);

    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String name,
            String description);


    List<Product> findByPriceBetweenAndSubcategory(
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Subcategory subCategory);

    List<Product> findByPriceBetween(
            BigDecimal minPrice,
            BigDecimal maxPrice);

<<<<<<< HEAD
}
=======

}

>>>>>>> 9ce32bb9bce45bd806a0c090a908f0753cf01bc6
