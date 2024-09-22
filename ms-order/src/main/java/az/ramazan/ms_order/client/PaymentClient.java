package az.ramazan.ms_order.client;

import az.ramazan.ms_order.client.decoder.CustomErrorDecoder;
import az.ramazan.ms_order.model.client.request.CreatePaymentRequest;
import az.ramazan.ms_order.model.client.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "ms-payment",
        url = "http://localhost:8082/v1/payments",
        configuration = CustomErrorDecoder.class

)
public interface PaymentClient {
    @PostMapping
    void pay(@RequestBody CreatePaymentRequest createPaymentRequest);

    @GetMapping("/order/{orderId}")
    PaymentResponse getPaymentByOrderId(@PathVariable Long orderId);


}
