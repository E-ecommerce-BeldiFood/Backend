package ma.beldifood.productcatalogservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ma.beldifood.productcatalogservice.entity.DtoRequest.ProductDtoRequest;
import ma.beldifood.productcatalogservice.entity.DtoRequest.ProductReviewDto;
=======
import lombok.RequiredArgsConstructor;
import ma.beldifood.productcatalogservice.entity.DtoRequest.ProductDtoRequest;
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
import ma.beldifood.productcatalogservice.entity.DtoResponse.ProductDtoResponse;
import ma.beldifood.productcatalogservice.exception.NotFoundException;
import ma.beldifood.productcatalogservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
<<<<<<< HEAD
import org.springframework.http.HttpStatus;
=======
<<<<<<< HEAD
=======
import org.springframework.http.HttpStatus;
>>>>>>> 9ce32bb9bce45bd806a0c090a908f0753cf01bc6
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@RestController
<<<<<<< HEAD
@AllArgsConstructor
@Transactional
@RequestMapping("/products")

=======
@RequiredArgsConstructor
@Transactional
@RequestMapping("/products")
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
public class ProductController {

    @Autowired
    private  final  ProductService productService;
    private final ObjectMapper objectMapper;

<<<<<<< HEAD


    @GetMapping("/{productId}/reviews")
    public ResponseEntity<?> getProductReviewById(@PathVariable Long productId){
        try {
            ProductReviewDto productReviewDto = productService.getProductReviewById(productId);
            return new ResponseEntity<>(productReviewDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

=======
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
    @PostMapping
    public ResponseEntity<ProductDtoResponse> createProduct(@RequestParam("product") String  productDtoRequestString, @RequestParam("productImage") MultipartFile productImage) throws JsonProcessingException {
        ProductDtoRequest productDtoRequest = objectMapper.readValue(productDtoRequestString, ProductDtoRequest.class);
        ProductDtoResponse product = productService.createProduct(productDtoRequest, productImage);
        //  ProductDtoResponse createdProduct = productService.createProduct(productDtoRequest);
        return ResponseEntity.ok(product);
    }

<<<<<<< HEAD

=======
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
    @GetMapping("/{id}")
    public ResponseEntity<ProductDtoResponse> getProductById(@PathVariable Long id) {
        ProductDtoResponse product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

<<<<<<< HEAD

=======
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
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



<<<<<<< HEAD
=======




>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
    @GetMapping("/search-and-filter")
    private ResponseEntity<List<ProductDtoResponse>> searchAndFilterProducts(
            @RequestParam(defaultValue = "0") BigDecimal minPrice,
            @RequestParam(defaultValue = "999") BigDecimal maxPrice,
            @RequestParam(defaultValue = "All") String subCategoryName) throws NotFoundException {

        List<ProductDtoResponse> products = productService.searchAndFilterProducts(minPrice, maxPrice, subCategoryName);

        return ResponseEntity.ok(products);
    }

    @GetMapping("/search")
    private ResponseEntity<List<ProductDtoResponse>> searchProducts(
            @RequestParam("query") String query) throws NotFoundException {
        List<ProductDtoResponse> allProducts = productService.searchProducts(query);
        if (allProducts.isEmpty()) {
            throw new NotFoundException("No product Found!!");
        }
        return ResponseEntity.ok(allProducts);
    }
<<<<<<< HEAD
    @GetMapping("/pagination")
    ResponseEntity<Page<ProductDtoResponse>> getProductsWithPagination(@RequestParam("pageNumber") int pageNumber,
                                                               @RequestParam("pageSize") int pageSize,
                                                               @RequestParam("field") String field,
                                                               @RequestParam("order") String order) {
        return ResponseEntity.ok(productService.findProductsWithPaginationAndSorting(pageNumber,pageSize,field,order));
}



//    @GetMapping("/pagination2")
//    public ResponseEntity<Page<ProductDtoResponse>> getProductsWithPagination2( @RequestParam(defaultValue = "0") int offset,
//                                                                     @RequestParam(defaultValue = "3") int pageSize,
//                                                                     @RequestParam(defaultValue = "id") String sortBy) {
//        Page<ProductDtoResponse> productsWithPagination = productService.findProductsWithPaginationAndSorting(offset, pageSize, sortBy);
//
//        System.out.println("in method get all products");
//
//        return ResponseEntity.ok(productsWithPagination);
//    }

=======

    @GetMapping("/pagination")
    private ResponseEntity<Page<ProductDtoResponse>> getProductsWithPagination(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        List<ProductDtoResponse> products = productService.getAllProducts();
        System.out.println(products.size());
        Page<ProductDtoResponse> productsWithPagination = productService.findProductsWithPaginationAndSorting(offset, pageSize, sortBy);
        return ResponseEntity.ok(productsWithPagination);
    }

    @GetMapping("/pagination2")
    public ResponseEntity<Page<ProductDtoResponse>> getProductsWithPagination2( @RequestParam(defaultValue = "0") int offset,
                                                                     @RequestParam(defaultValue = "10") int pageSize,
                                                                     @RequestParam(defaultValue = "id") String sortBy) {
        Page<ProductDtoResponse> productsWithPagination = productService.findProductsWithPaginationAndSorting(offset, pageSize, sortBy);

        System.out.println("in method get all products");
        List<ProductDtoResponse> products = productService.getAllProducts();
        return ResponseEntity.ok(productsWithPagination);
    }
<<<<<<< HEAD
=======

>>>>>>> 9ce32bb9bce45bd806a0c090a908f0753cf01bc6
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
}
