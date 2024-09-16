package az.ramazan.ms_payment.service.abstraction;

import az.ramazan.ms_payment.model.request.CreatePaymentRequest;

public interface PaymentService {
    void pay(CreatePaymentRequest createPaymentRequest);
}
