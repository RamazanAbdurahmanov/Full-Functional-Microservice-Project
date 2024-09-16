package az.ramazan.ms_payment.controller;

import az.ramazan.ms_payment.model.request.CreatePaymentRequest;
import az.ramazan.ms_payment.model.response.PaymentResponse;
import az.ramazan.ms_payment.service.abstraction.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    @ResponseStatus(CREATED)
    public PaymentResponse pay(@RequestBody CreatePaymentRequest createPaymentRequest) {
       return paymentService.pay(createPaymentRequest);
    }

}
