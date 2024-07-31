package az.ramazan.products.mapper;

import az.ramazan.products.dao.entity.ProductEntity;
import az.ramazan.products.model.request.CreateProductRequest;
import az.ramazan.products.model.response.ProductResponse;

public enum ProductMapper {
    PRODUCT_MAPPER;

    public ProductEntity buildProductEntity(CreateProductRequest request) {
        return ProductEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();
    }

    public ProductResponse buildProductResponse(ProductEntity productEntity) {
        return ProductResponse.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .description(productEntity.getDescription())
                .quantity(productEntity.getQuantity())
                .build();
    }
}
