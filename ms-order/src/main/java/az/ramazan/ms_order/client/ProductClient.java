package az.ramazan.ms_order.client;

import az.ramazan.ms_order.client.decoder.CustomErrorDecoder;
import az.ramazan.ms_order.model.client.response.ProductResponse;
import az.ramazan.ms_order.model.client.request.ReduceQuantityRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name="ms-product",
        url="http://localhost:9999/v1/products",
        configuration = CustomErrorDecoder.class
)
public interface ProductClient {
    @PostMapping("/reduce-quantity")
    void reduceQuantity(@RequestBody ReduceQuantityRequest reduceQuantityrequest);

    @GetMapping("/{id}")
    ProductResponse getProductById(@PathVariable Long id);

}
