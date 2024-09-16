package az.ramazan.ms_payment.service.concrete;

import az.ramazan.ms_payment.mapper.PaymentMapper;
import az.ramazan.ms_payment.model.request.CreatePaymentRequest;
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
    public void pay(CreatePaymentRequest createPaymentRequest) {
        paymentRepository.save(PAYMENT_MAPPER.buildPaymentEntity(createPaymentRequest));


    }
}
