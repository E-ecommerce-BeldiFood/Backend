package ma.beldifood.productcatalogservice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import ma.beldifood.productcatalogservice.entity.DtoRequest.ProductDtoRequest;
import ma.beldifood.productcatalogservice.entity.DtoResponse.ProductDtoResponse;
import ma.beldifood.productcatalogservice.entity.Product;
import ma.beldifood.productcatalogservice.entity.Subcategory;
import ma.beldifood.productcatalogservice.exception.NotFoundException;
import ma.beldifood.productcatalogservice.repository.ProductRepository;
import ma.beldifood.productcatalogservice.repository.SubcategoryRepository;
import ma.beldifood.productcatalogservice.utils.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImp implements ProductService{

    private final ProductRepository productRepository;
    private final SubcategoryRepository subcategoryRepository;

    private final FirebaseStorageService firebaseStorageService; // Assume a service for interacting with Firebase Storage

    public ProductDtoResponse createProduct(ProductDtoRequest productDtoRequest, MultipartFile file) {
        Product product = Mapping.mapToProduct(productDtoRequest);

        // Upload image to Firebase Storage and get URL
        String imageUrl = firebaseStorageService.upload(file);
        product.setImageUrl(imageUrl);

        Subcategory subcategory= subcategoryRepository.findById(productDtoRequest.getSubcategoryId())
                .orElseThrow(() -> new NoSuchElementException("Subcategory not found with id: " + productDtoRequest.getSubcategoryId()));
        product.setSubcategory(subcategory);
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
        return productRepository.findBySubcategory_Category_Id(categoryId).stream()
                .map(Mapping::mapToProductResponseDto)
                .toList();
    }

    @Override
    public List<ProductDtoResponse> getProductsBySubcategory(Long subcategoryId) {
        return productRepository.findBySubcategory_Id(subcategoryId).stream()
                .map(Mapping::mapToProductResponseDto)
                .toList();
    }





    @Override
    public Page<ProductDtoResponse> findProductsWithPaginationAndSorting(Integer offset, Integer pageSize, String field) {
        Page<Product> products = productRepository.findAll(PageRequest.of(offset, pageSize, Sort.by(field)));
        //return products.map(Mapping::mapToProductResponseDto);
        return null;
    }

    @Override
    public List<ProductDtoResponse> searchProducts(String query) {
        List<Product> products = productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
        return products.stream()
                .map(Mapping::mapToProductResponseDto)
                .toList();
    }

    @Override
    public List<ProductDtoResponse> searchAndFilterProducts(BigDecimal minPrice, BigDecimal maxPrice, String subCategoryName) throws NotFoundException {
        if (subCategoryName.equals("All")) {
            List<Product> productsWithoutSubCategory = productRepository.findByPriceBetween(minPrice, maxPrice);
            if (productsWithoutSubCategory.isEmpty()) {
                throw new NotFoundException("No Product Found, change filter values!");
            }
            return productsWithoutSubCategory.stream()
                    .map(Mapping::mapToProductResponseDto)
                    .toList();
        } else {
            Subcategory subCategory = subcategoryRepository.findByName(subCategoryName);
            List<Product> productsWithSubCategory = productRepository.findByPriceBetweenAndSubcategory(minPrice, maxPrice, subCategory);
            if (productsWithSubCategory.isEmpty()) {
                throw new NotFoundException("No Product Found, change filter values!");
            }

            return productsWithSubCategory.stream()
                    .map(Mapping::mapToProductResponseDto)
                    .toList();
        }
    }

}