package ma.beldifood.productcatalogservice.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.beldifood.productcatalogservice.entity.DtoRequest.ProductDtoRequest;
import ma.beldifood.productcatalogservice.entity.DtoResponse.ProductDtoResponse;
import ma.beldifood.productcatalogservice.entity.Product;
import ma.beldifood.productcatalogservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {


    private  final  ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductDtoResponse> createProduct(@Valid @ModelAttribute ProductDtoRequest productDtoRequest) {
        Product product = productService.createProduct();
        //  ProductDtoResponse createdProduct = productService.createProduct(productDtoRequest);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDtoResponse> getProductById(@PathVariable Long id) {
        ProductDtoResponse product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDtoResponse>> getAllProducts() {
        List<ProductDtoResponse> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDtoResponse> updateProduct(@PathVariable Long id, @Valid @ModelAttribute ProductDtoRequest productDtoRequest) {
        ProductDtoResponse updatedProduct = productService.updateProduct(id, productDtoRequest);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
