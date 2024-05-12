package ma.beldifood.productcatalogservice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
import ma.beldifood.productcatalogservice.client.ReviewServiceClient;
import ma.beldifood.productcatalogservice.entity.DtoRequest.ProductDtoRequest;
import ma.beldifood.productcatalogservice.entity.DtoRequest.ProductReviewDto;
import ma.beldifood.productcatalogservice.entity.DtoRequest.Review;
=======
<<<<<<< HEAD
=======
import ma.beldifood.productcatalogservice.component.RabbitMqGetProductReviews;
>>>>>>> 9ce32bb9bce45bd806a0c090a908f0753cf01bc6
import ma.beldifood.productcatalogservice.entity.DtoRequest.ProductDtoRequest;
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
import ma.beldifood.productcatalogservice.entity.DtoResponse.ProductDtoResponse;
import ma.beldifood.productcatalogservice.entity.Product;
import ma.beldifood.productcatalogservice.entity.Subcategory;
import ma.beldifood.productcatalogservice.exception.NotFoundException;
import ma.beldifood.productcatalogservice.repository.ProductRepository;
import ma.beldifood.productcatalogservice.repository.SubcategoryRepository;
import ma.beldifood.productcatalogservice.utils.Mapping;
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 9ce32bb9bce45bd806a0c090a908f0753cf01bc6
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
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
<<<<<<< HEAD
    private final ReviewServiceClient reviewServiceClient;

    private final FirebaseStorageService firebaseStorageService; // Assume a service for interacting with Firebase Storage





    public ProductReviewDto getProductReviewById(Long id) throws Exception{
        var product = productRepository.findById(id).orElseThrow(() ->new NotFoundException("Product not found"));
        List<Review> reviews = reviewServiceClient.getReviewsByProductId(product.getId());
        return Mapping.mapToProductReviewDto(product, reviews);
    }

=======

    private final FirebaseStorageService firebaseStorageService; // Assume a service for interacting with Firebase Storage

<<<<<<< HEAD
=======
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private RabbitMqGetProductReviews rabbitMqGetProductReviews;


>>>>>>> 9ce32bb9bce45bd806a0c090a908f0753cf01bc6
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
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
<<<<<<< HEAD

=======
<<<<<<< HEAD
=======

>>>>>>> 9ce32bb9bce45bd806a0c090a908f0753cf01bc6
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
        return Mapping.mapToProductResponseDto(product);
    }



<<<<<<< HEAD
//    public Page<ProductDtoResponse> getAllProducts(int pageNumber, int pageSize, String field, String order) {
//        PageRequest pageRequest = PageRequest.of(
//                pageNumber,
//                pageSize,
//                Sort.by(order.equalsIgnoreCase("desc")?
//                        Sort.Direction.DESC:
//                        Sort.Direction.ASC,
//                        field)
//        );
//        return ProductDtoResponse.findAll(pageRequest).map(Mapping::mapToProductResponseDto);
//    }
        @Override
    public Page<ProductDtoResponse> findProductsWithPaginationAndSorting(int pageNumber, int pageSize, String field, String order) {
        PageRequest pageRequest = PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by(order.equalsIgnoreCase("desc")?
                        Sort.Direction.DESC:
                        Sort.Direction.ASC,
                        field)
        );

//        Page<Product> products = productRepository.findAll(pageRequest);


        return productRepository.findAll(pageRequest).map(Mapping::mapToProductResponseDto);

    }
=======
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
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





<<<<<<< HEAD
//    @Override
//    public Page<ProductDtoResponse> findProductsWithPaginationAndSorting(Integer offset, Integer pageSize, String field) {
//        Pageable pageable= PageRequest.of(offset, pageSize, Sort.by(field));
//
//        Page<Product> products = productRepository.findAll(pageable);
//
//
//        return products.map(Mapping::mapToProductResponseDto);
//
//    }


=======
    @Override
    public Page<ProductDtoResponse> findProductsWithPaginationAndSorting(Integer offset, Integer pageSize, String field) {
        Page<Product> products = productRepository.findAll(PageRequest.of(offset, pageSize, Sort.by(field)));
        //return products.map(Mapping::mapToProductResponseDto);
        return null;
    }
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c

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

<<<<<<< HEAD



=======
<<<<<<< HEAD
=======


>>>>>>> 9ce32bb9bce45bd806a0c090a908f0753cf01bc6
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
}