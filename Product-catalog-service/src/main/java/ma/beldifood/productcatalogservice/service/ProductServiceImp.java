package ma.beldifood.productcatalogservice.service;

import lombok.RequiredArgsConstructor;
import ma.beldifood.productcatalogservice.entity.DtoRequest.ProductDtoRequest;
import ma.beldifood.productcatalogservice.entity.DtoResponse.ProductDtoResponse;
import ma.beldifood.productcatalogservice.entity.Product;
import ma.beldifood.productcatalogservice.repository.ProductRepository;
import ma.beldifood.productcatalogservice.utils.Mapping;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService{

    private final ProductRepository productRepository;


    private final FirebaseStorageService firebaseStorageService; // Assume a service for interacting with Firebase Storage

    public ProductDtoResponse createProduct(ProductDtoRequest productDtoRequest, MultipartFile fileName) {
        Product product = Mapping.mapToProduct(productDtoRequest);

            String fileName = multipartFile.getOriginalFilename();                        // to get original file name
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));  // to generated random string values for file name.

            File file = this.convertToFile(multipartFile, fileName);                      // to convert multipartFile to File
            String URL = this.uploadFile(file, fileName);                                   // to get uploaded file link
            file.delete();
            return URL;



        // Upload image to Firebase Storage and get URL
        String imageUrl = firebaseStorageService.uploadImage(productDtoRequest.getImageFile());
        product.setImageUrl(imageUrl);

        Product savedProduct = productRepository.save(product);
        return convertToDtoResponse(savedProduct);
    }
    /*
    public ProductDtoResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        return convertToDtoResponse(product);
    }


    public List<ProductDtoResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::convertToDtoResponse).collect(Collectors.toList());
    }

    public ProductDtoResponse updateProduct(Long id, ProductDtoRequest productDtoRequest) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));

        existingProduct.setName(productDtoRequest.getName());
        existingProduct.setPrice(productDtoRequest.getPrice());
        existingProduct.setDescription(productDtoRequest.getDescription());

        // Update image if provided
        if (productDtoRequest.getImageFile() != null) {
            String imageUrl = firebaseStorageService.uploadImage(productDtoRequest.getImageFile());
            existingProduct.setImageUrl(imageUrl);
        }

        Product updatedProduct = productRepository.save(existingProduct);
        return convertToDtoResponse(updatedProduct);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }*/



}