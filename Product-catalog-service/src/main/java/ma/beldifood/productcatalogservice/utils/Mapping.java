package ma.beldifood.productcatalogservice.utils;

import lombok.NoArgsConstructor;
import ma.beldifood.productcatalogservice.entity.DtoRequest.ProductDtoRequest;
import ma.beldifood.productcatalogservice.entity.DtoResponse.ProductDtoResponse;
import ma.beldifood.productcatalogservice.entity.Product;
import org.modelmapper.ModelMapper;

@NoArgsConstructor
public class Mapping {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static Product mapToProduct(ProductDtoRequest productDtoRequest) {
        return modelMapper.map(productDtoRequest, Product.class);
    }

    public static ProductDtoResponse mapToProductResponseDto(Product product) {
        return modelMapper.map(product, ProductDtoResponse.class);
    }




}