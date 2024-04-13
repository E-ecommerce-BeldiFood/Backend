package ma.beldifood.productcatalogservice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import ma.beldifood.productcatalogservice.entity.DtoRequest.ProductDtoRequest;
import ma.beldifood.productcatalogservice.entity.DtoResponse.ProductDtoResponse;
import ma.beldifood.productcatalogservice.entity.Product;
import ma.beldifood.productcatalogservice.repository.ProductRepository;
import ma.beldifood.productcatalogservice.utils.Mapping;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImp implements ProductService{

    private final ProductRepository productRepository;

    private final FirebaseStorageService firebaseStorageService; // Assume a service for interacting with Firebase Storage

    public ProductDtoResponse createProduct(ProductDtoRequest productDtoRequest, MultipartFile file) {
        Product product = Mapping.mapToProduct(productDtoRequest);
        product.setProductId(null); // model mapper initialize id with value of 1 by default
        // Upload image to Firebase Storage and get URL
        String imageUrl = firebaseStorageService.upload(file);
        product.setImageUrl(imageUrl);

        Product savedProduct = productRepository.save(product);

        return Mapping.mapToProductResponseDto(savedProduct);
    }

    public ProductDtoResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        return Mapping.mapToProductResponseDto(product);
    }



    public List<ProductDtoResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(Mapping::mapToProductResponseDto).collect(Collectors.toList());
    }

    public ProductDtoResponse updateProduct(Long id, ProductDtoRequest productDtoRequest, MultipartFile file) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));

        existingProduct.setName(productDtoRequest.getName());
        existingProduct.setPrice(productDtoRequest.getPrice());
        existingProduct.setDescription(productDtoRequest.getDescription());

        // Update image if provided
        if (file != null) {
            String imageUrl = firebaseStorageService.upload(file);
            existingProduct.setImageUrl(imageUrl);
        }

        Product updatedProduct = productRepository.save(existingProduct);
        return Mapping.mapToProductResponseDto(updatedProduct);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }

    @Override
    public List<ProductDtoResponse> getProductsByCategory(Long categoryId) {
        return productRepository.findBySubcategory_Category_CategoryId(categoryId).stream()
                .map(Mapping::mapToProductResponseDto)
                .toList();
    }

    @Override
    public List<ProductDtoResponse> getProductsBySubcategory(Long subcategoryId) {
        return productRepository.findBySubcategory_SubcategoryId(subcategoryId).stream()
                .map(Mapping::mapToProductResponseDto)
                .toList();
    }

}