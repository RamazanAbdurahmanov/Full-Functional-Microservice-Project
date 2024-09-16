package az.ramazan.ms_order.client;

import az.ramazan.ms_order.client.decoder.CustomErrorDecoder;
import az.ramazan.ms_order.model.client.request.CreatePaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "ms-payment",
        url ="localhost:8082/v1/payments",
        configuration= CustomErrorDecoder.class

)
public interface PaymentClient {
    @PostMapping
     void pay(@RequestBody CreatePaymentRequest createPaymentRequest);

}
