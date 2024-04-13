package ma.beldifood.productcatalogservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import ma.beldifood.productcatalogservice.entity.DtoRequest.ProductDtoRequest;
import ma.beldifood.productcatalogservice.entity.DtoResponse.ProductDtoResponse;
import ma.beldifood.productcatalogservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {


    private  final  ProductService productService;
    private final ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<ProductDtoResponse> createProduct(@RequestParam("product") String  productDtoRequestString, @RequestParam("productImage") MultipartFile productImage) throws JsonProcessingException {
        ProductDtoRequest productDtoRequest = objectMapper.readValue(productDtoRequestString, ProductDtoRequest.class);
        ProductDtoResponse product = productService.createProduct(productDtoRequest, productImage);
        //  ProductDtoResponse createdProduct = productService.createProduct(productDtoRequest);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDtoResponse> getProductById(@PathVariable Long id) {
        ProductDtoResponse product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductDtoResponse>> getAllProducts() {
        System.out.println("in method get all products");
        List<ProductDtoResponse> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDtoResponse> updateProduct(@PathVariable Long id, @RequestParam("product") String  productDtoRequestString, @RequestParam("productImage") MultipartFile productImage ) throws JsonProcessingException {
        ProductDtoRequest productDtoRequest = objectMapper.readValue(productDtoRequestString, ProductDtoRequest.class);

        ProductDtoResponse updatedProduct = productService.updateProduct(id, productDtoRequest, productImage);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<ProductDtoResponse>> getProductsByCategory(@PathVariable Long categoryId) {
        List<ProductDtoResponse> products = productService.getProductsByCategory(categoryId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/by-subcategory/{subcategoryId}")
    public ResponseEntity<List<ProductDtoResponse>> getProductsBySubcategory(@PathVariable Long subcategoryId) {
        List<ProductDtoResponse> products = productService.getProductsBySubcategory(subcategoryId);
        return ResponseEntity.ok(products);
    }
}
