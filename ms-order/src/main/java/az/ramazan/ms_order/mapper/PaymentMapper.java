package az.ramazan.ms_order.mapper;

import az.ramazan.ms_order.dao.entity.OrderEntity;
import az.ramazan.ms_order.model.client.request.CreatePaymentRequest;
import az.ramazan.ms_order.model.request.CreateOrderRequest;

import java.math.BigDecimal;
import java.util.UUID;

public enum PaymentMapper {
    PAYMENT_MAPPER;

    public CreatePaymentRequest buildCreatePaymentRequest(CreateOrderRequest createOrderRequest,
                                                          OrderEntity orderEntity,
                                                          BigDecimal totalAmount) {
        return CreatePaymentRequest.builder()
                .orderId(orderEntity.getId())
                .paymentType(createOrderRequest.getPaymentType())
                .amount(totalAmount)
                .referenceNumber(UUID.randomUUID().toString())
                .build();
    }
}
