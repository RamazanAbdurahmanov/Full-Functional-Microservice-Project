package az.ramazan.products.service.abstraction;

import az.ramazan.products.model.request.CreateProductRequest;
import az.ramazan.products.model.response.ProductResponse;

public interface ProductService {
    void createProduct(CreateProductRequest request);
    ProductResponse getProductById(Long id);

}
