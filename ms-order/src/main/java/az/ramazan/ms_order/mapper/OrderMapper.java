package az.ramazan.ms_order.mapper;

import az.ramazan.ms_order.dao.entity.OrderEntity;
import az.ramazan.ms_order.model.client.response.PaymentResponse;
import az.ramazan.ms_order.model.client.response.ProductResponse;
import az.ramazan.ms_order.model.request.CreateOrderRequest;
import az.ramazan.ms_order.model.response.OrderResponse;


import static az.ramazan.ms_order.model.enums.OrderStatus.PENDING;
import static java.time.LocalDateTime.now;

public enum OrderMapper {
    ORDER_MAPPER;

    public OrderEntity buildOrderEntity(CreateOrderRequest createOrderRequest) {
        return OrderEntity.builder()
                .productId(createOrderRequest.getProductId())
                .quantity(createOrderRequest.getQuantity())
                .status(PENDING)
                .createdAt(now())
                .build();
    }

    public OrderResponse buildOrderResponse(OrderEntity orderEntity,
                                            ProductResponse productResponse,
                                            PaymentResponse paymentResponse) {
        return OrderResponse.builder()
                .id(orderEntity.getId())
                .productId(orderEntity.getProductId())
                .quantity(orderEntity.getQuantity())
                .amount(orderEntity.getAmount())
                .status(orderEntity.getStatus())
                .createdAt(orderEntity.getCreatedAt())
                .product(productResponse)
                .payment(paymentResponse)
                .build();
    }


}
