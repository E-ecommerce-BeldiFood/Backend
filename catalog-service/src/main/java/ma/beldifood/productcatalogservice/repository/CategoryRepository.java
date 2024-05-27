package ma.beldifood.productcatalogservice.repository;

import ma.beldifood.productcatalogservice.entity.Category;
import ma.beldifood.productcatalogservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByNameContainingIgnoreCase(String name);
}