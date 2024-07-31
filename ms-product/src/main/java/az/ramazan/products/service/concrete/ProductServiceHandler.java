package az.ramazan.products.service.concrete;

import az.ramazan.products.dao.repository.ProductRepository;
import az.ramazan.products.exception.NotFoundException;
import static az.ramazan.products.model.enums.ErrorMessage.*;
import az.ramazan.products.model.request.CreateProductRequest;
import az.ramazan.products.model.response.ProductResponse;
import az.ramazan.products.service.abstraction.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static az.ramazan.products.mapper.ProductMapper.*;

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
                        String.format(
                                PRODUCT_NOT_FOUND.getMessage(),
                                id)
                ));
    }
}
