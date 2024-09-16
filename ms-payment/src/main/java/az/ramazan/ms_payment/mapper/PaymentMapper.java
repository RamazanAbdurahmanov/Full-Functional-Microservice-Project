package az.ramazan.ms_payment.mapper;

import az.ramazan.ms_payment.dao.entity.PaymentEntity;
import az.ramazan.ms_payment.model.enums.PaymentStatus;
import az.ramazan.ms_payment.model.request.CreatePaymentRequest;

import java.time.LocalDateTime;

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
}
