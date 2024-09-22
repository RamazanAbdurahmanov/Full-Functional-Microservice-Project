package az.ramazan.ms_payment.mapper;

import az.ramazan.ms_payment.dao.entity.PaymentEntity;
import az.ramazan.ms_payment.model.request.CreatePaymentRequest;
import az.ramazan.ms_payment.model.response.PaymentResponse;


import static az.ramazan.ms_payment.model.enums.PaymentStatus.*;
import static java.time.LocalDateTime.*;

public enum PaymentMapper {
    PAYMENT_MAPPER;
    public PaymentEntity buildPaymentEntity(CreatePaymentRequest createPaymentRequest){
        return PaymentEntity.builder()
                .orderId(createPaymentRequest.getOrderId())
                .status(SUCCESS)
                .paymentType(createPaymentRequest.getPaymentType())
                .referenceNumber(createPaymentRequest.getReferenceNumber())
                .amount(createPaymentRequest.getAmount())
                .createdAt(now())
                .build();
    }
    public PaymentResponse buildPaymentResponse(PaymentEntity paymentEntity){
        return PaymentResponse.builder()
                .id(paymentEntity.getId())
                .status(paymentEntity.getStatus())
                .createdAt(paymentEntity.getCreatedAt())
                .paymentType(paymentEntity.getPaymentType())
                .build();

    }
}
