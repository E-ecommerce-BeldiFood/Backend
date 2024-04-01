package ma.beldifood.productcatalogservice.controller;

import ma.beldifood.productcatalogservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Implement REST endpoints for category management (e.g., createCategory, getCategoryById, etc.)
}