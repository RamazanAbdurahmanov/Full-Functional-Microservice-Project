package az.ramazan.products.service.concrete;

import az.ramazan.products.dao.repository.ProductRepository;
import az.ramazan.products.exception.InsufficientQuantityException;
import az.ramazan.products.exception.NotFoundException;
import static az.ramazan.products.model.enums.ErrorMessage.*;
import az.ramazan.products.model.request.CreateProductRequest;
import az.ramazan.products.model.request.ReduceQuantityRequest;
import az.ramazan.products.model.response.ProductResponse;
import az.ramazan.products.service.abstraction.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static az.ramazan.products.mapper.ProductMapper.*;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProductServiceHandler implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public void createProduct(CreateProductRequest createProductRequest) {
        productRepository.save(PRODUCT_MAPPER.buildProductEntity(createProductRequest));

    }

    @Override
    public ProductResponse getProductById(Long id) {
       return   productRepository.findById(id)
               .map(PRODUCT_MAPPER::buildProductResponse)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                PRODUCT_NOT_FOUND.getMessage(),
                                id)
                ));
    }

    @Override
    public void reduceQuantity(ReduceQuantityRequest reduceQuantityRequest) {
        var productEntity =
                productRepository.findById(reduceQuantityRequest.getProductId())
                        .orElseThrow(()->new NotFoundException(format(
                                PRODUCT_NOT_FOUND.getMessage(),
                                reduceQuantityRequest.getProductId()
                        )));
        if(productEntity.getQuantity()<reduceQuantityRequest.getQuantity()){
            throw new InsufficientQuantityException(
                    format(INSUFFICIENT_QUANTITY.getMessage(),
                            reduceQuantityRequest.getProductId())
            );
        }
        productEntity.setQuantity(productEntity.getQuantity()-reduceQuantityRequest.getQuantity());
        productRepository.save(productEntity);
    }
}
