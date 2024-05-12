package ma.beldifood.productcatalogservice.service;

import ma.beldifood.productcatalogservice.entity.DtoRequest.ProductDtoRequest;
<<<<<<< HEAD
import ma.beldifood.productcatalogservice.entity.DtoRequest.ProductReviewDto;
=======
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
import ma.beldifood.productcatalogservice.entity.DtoResponse.ProductDtoResponse;
import ma.beldifood.productcatalogservice.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
<<<<<<< HEAD
   
=======
    public List<ProductDtoResponse> getAllProducts();
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
    public ProductDtoResponse getProductById(Long id);
    public ProductDtoResponse createProduct(ProductDtoRequest productDto, MultipartFile file);
    public void deleteProduct(Long id);

<<<<<<< HEAD
    Page<ProductDtoResponse> findProductsWithPaginationAndSorting(int pageNumber, int pageSize, String field, String order);

=======
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
    public ProductDtoResponse updateProduct(Long id, ProductDtoRequest productDtoRequest, MultipartFile productImage);

    List<ProductDtoResponse> getProductsByCategory(Long categoryId);
    List<ProductDtoResponse> getProductsBySubcategory(Long subcategoryId);


<<<<<<< HEAD
//    Page<ProductDtoResponse> findProductsWithPaginationAndSorting(Integer offset, Integer pageSize, String field);
=======
    Page<ProductDtoResponse> findProductsWithPaginationAndSorting(Integer offset, Integer pageSize, String field);
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c

    List<ProductDtoResponse> searchProducts(String query);

    List<ProductDtoResponse> searchAndFilterProducts(BigDecimal minPrice, BigDecimal maxPrice, String subCategoryName) throws NotFoundException;
<<<<<<< HEAD
    ProductReviewDto getProductReviewById(Long id) throws Exception;

    List<ProductDtoResponse> getAllProducts();
=======
<<<<<<< HEAD
=======

>>>>>>> 9ce32bb9bce45bd806a0c090a908f0753cf01bc6
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
}
