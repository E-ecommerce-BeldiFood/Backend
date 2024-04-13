package ma.beldifood.productcatalogservice.service;

import ma.beldifood.productcatalogservice.entity.DtoRequest.ProductDtoRequest;
import ma.beldifood.productcatalogservice.entity.DtoResponse.ProductDtoResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    public List<ProductDtoResponse> getAllProducts();
    public ProductDtoResponse getProductById(Long id);
    public ProductDtoResponse createProduct(ProductDtoRequest productDto, MultipartFile file);
    public void deleteProduct(Long id);

    public ProductDtoResponse updateProduct(Long id, ProductDtoRequest productDtoRequest, MultipartFile productImage);

    List<ProductDtoResponse> getProductsByCategory(Long categoryId);
    List<ProductDtoResponse> getProductsBySubcategory(Long subcategoryId);
}
