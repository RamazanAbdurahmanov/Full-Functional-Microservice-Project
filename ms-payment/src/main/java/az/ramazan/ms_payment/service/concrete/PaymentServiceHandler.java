package az.ramazan.ms_payment.service.concrete;

import az.ramazan.ms_payment.exception.NotFoundException;
import az.ramazan.ms_payment.mapper.PaymentMapper;
import az.ramazan.ms_payment.model.request.CreatePaymentRequest;
import az.ramazan.ms_payment.model.response.PaymentResponse;
import az.ramazan.ms_payment.repository.PaymentRepository;
import az.ramazan.ms_payment.service.abstraction.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static az.ramazan.ms_payment.mapper.PaymentMapper.*;

@Service
@RequiredArgsConstructor
public class PaymentServiceHandler implements PaymentService {
    private final PaymentRepository paymentRepository;
    @Override
    public PaymentResponse pay(CreatePaymentRequest createPaymentRequest) {
        var paymentEntity= PAYMENT_MAPPER.buildPaymentEntity(createPaymentRequest);
        paymentRepository.save(paymentEntity);
        return PAYMENT_MAPPER.buildPaymentResponse(paymentEntity);



    }

    @Override
    public PaymentResponse getPaymentByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId)
                .map(PAYMENT_MAPPER::buildPaymentResponse)
                .orElseThrow(()->new NotFoundException(
                        "Payment not found with order id :"+orderId));
    }
}
