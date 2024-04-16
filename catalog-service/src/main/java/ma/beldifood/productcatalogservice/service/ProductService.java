package ma.beldifood.productcatalogservice.service;

import ma.beldifood.productcatalogservice.entity.DtoRequest.ProductDtoRequest;
import ma.beldifood.productcatalogservice.entity.DtoResponse.ProductDtoResponse;
import ma.beldifood.productcatalogservice.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    public List<ProductDtoResponse> getAllProducts();
    public ProductDtoResponse getProductById(Long id);
    public ProductDtoResponse createProduct(ProductDtoRequest productDto, MultipartFile file);
    public void deleteProduct(Long id);

    public ProductDtoResponse updateProduct(Long id, ProductDtoRequest productDtoRequest, MultipartFile productImage);

    List<ProductDtoResponse> getProductsByCategory(Long categoryId);
    List<ProductDtoResponse> getProductsBySubcategory(Long subcategoryId);


    Page<ProductDtoResponse> findProductsWithPaginationAndSorting(Integer offset, Integer pageSize, String field);

    List<ProductDtoResponse> searchProducts(String query);

    List<ProductDtoResponse> searchAndFilterProducts(BigDecimal minPrice, BigDecimal maxPrice, String subCategoryName) throws NotFoundException;
}
