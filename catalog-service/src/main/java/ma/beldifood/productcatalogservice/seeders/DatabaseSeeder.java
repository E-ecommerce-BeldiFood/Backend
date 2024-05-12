package ma.beldifood.productcatalogservice.seeders;


import ma.beldifood.productcatalogservice.entity.Category;
import ma.beldifood.productcatalogservice.entity.Product;
import ma.beldifood.productcatalogservice.entity.Subcategory;
import ma.beldifood.productcatalogservice.repository.CategoryRepository;
import ma.beldifood.productcatalogservice.repository.ProductRepository;
import ma.beldifood.productcatalogservice.repository.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public DatabaseSeeder(CategoryRepository categoryRepository, SubcategoryRepository subcategoryRepository,
                          ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedCategories();
        seedProducts();
    }

    private void seedCategories() {
        Category category1 = new Category();
        category1.setName("Category 1");

        Subcategory subcategory1 = new Subcategory();
        subcategory1.setName("Subcategory 1");
        subcategory1.setCategory(category1);

        categoryRepository.save(category1);
        subcategoryRepository.save(subcategory1);
    }

    private void seedProducts() {
        Subcategory subcategory1 = subcategoryRepository.findByName("Subcategory 1");

        if (subcategory1 != null) {
            Product product1 = new Product();
            product1.setName("Product 1");
            product1.setPrice(new BigDecimal("10.99"));
            product1.setDescription("Description for Product 1");
            product1.setImageUrl("https://example.com/image1.jpg");
<<<<<<< HEAD
            product1.setTimeToPrepareInMinute("15 min");
=======
            product1.setTimeToPrepareInMinute(15);
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
            product1.setAvailability(true);
            product1.setCreatedAt(LocalDateTime.now());
            product1.setUpdatedAt(LocalDateTime.now());
            product1.setPositiveFeedback(0);
            product1.setNegativeFeedback(0);
            product1.setSubcategory(subcategory1);

            productRepository.save(product1);
        }
    }

}