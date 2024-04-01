package ma.beldifood.productcatalogservice.controller;

import ma.beldifood.productcatalogservice.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subcategories")
public class SubcategoryController {

    @Autowired
    private SubcategoryService subcategoryService;

    // Implement REST endpoints for subcategory management (e.g., createSubcategory, getSubcategoryById, etc.)
}