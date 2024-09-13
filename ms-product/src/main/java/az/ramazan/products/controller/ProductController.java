package az.ramazan.products.controller;

import az.ramazan.products.model.request.CreateProductRequest;
import az.ramazan.products.model.request.ReduceQuantityRequest;
import az.ramazan.products.model.response.ProductResponse;
import az.ramazan.products.service.abstraction.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody @Valid CreateProductRequest createProductRequest) {
        productService.createProduct(createProductRequest);
    }
    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping("/reduce-quantity")
    @ResponseStatus(NO_CONTENT)
    public void reduceQuantity(@RequestBody @Valid ReduceQuantityRequest reduceQuantityRequest){
        productService.reduceQuantity(reduceQuantityRequest);
    }


}
