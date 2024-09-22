package az.ramazan.ms_payment.service.abstraction;

import az.ramazan.ms_payment.model.request.CreatePaymentRequest;
import az.ramazan.ms_payment.model.response.PaymentResponse;

public interface PaymentService {
    PaymentResponse pay(CreatePaymentRequest createPaymentRequest);

    PaymentResponse getPaymentByOrderId(Long orderId);
}
