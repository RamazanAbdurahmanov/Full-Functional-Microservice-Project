package az.ramazan.ms_order.service.concrete;

import az.ramazan.ms_order.client.PaymentClient;
import az.ramazan.ms_order.client.ProductClient;
import az.ramazan.ms_order.dao.repository.OrderRepository;
import az.ramazan.ms_order.exception.NotFoundException;
import az.ramazan.ms_order.mapper.PaymentMapper;
import az.ramazan.ms_order.model.client.request.CreatePaymentRequest;
import az.ramazan.ms_order.model.client.request.ReduceQuantityRequest;
import az.ramazan.ms_order.model.client.response.PaymentResponse;
import az.ramazan.ms_order.model.request.CreateOrderRequest;
import az.ramazan.ms_order.model.response.OrderResponse;
import az.ramazan.ms_order.service.abstraction.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.UUID;

import static az.ramazan.ms_order.mapper.OrderMapper.ORDER_MAPPER;
import static az.ramazan.ms_order.mapper.PaymentMapper.PAYMENT_MAPPER;
import static az.ramazan.ms_order.model.enums.ErrorMessage.*;
import static az.ramazan.ms_order.model.enums.OrderStatus.*;
import static java.lang.String.format;
import static java.math.BigDecimal.*;

@Service
@RequiredArgsConstructor
public class OrderServiceHandler implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;


    @Override
    @Transactional
    public void createOrder(CreateOrderRequest createOrderRequest) {
        var orderEntity = ORDER_MAPPER.buildOrderEntity(createOrderRequest);
        var productResponse = productClient.getProductById(createOrderRequest.getProductId());
        var totalAmount = productResponse.getPrice().multiply(valueOf(createOrderRequest.getQuantity()));

        orderEntity.setAmount(totalAmount);
        var reduceQuantityRequest = new ReduceQuantityRequest(
                createOrderRequest.getProductId(),
                createOrderRequest.getQuantity()
        );
        orderRepository.save(orderEntity);
        productClient.reduceQuantity(reduceQuantityRequest);

        try {
            paymentClient.pay(
                    PAYMENT_MAPPER.buildCreatePaymentRequest(
                            createOrderRequest,
                            orderEntity,
                            totalAmount
                    ));
            orderEntity.setStatus(APPROVED);
        } catch (Exception e) {
            orderEntity.setStatus(REJECTED);
        }


    }

    @Override
    public OrderResponse getOrderById(Long id) {
        var orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        format(ORDER_NOT_FOUND.getMessage(),
                                id
                        )));
        var productResponse = productClient.getProductById(orderEntity.getProductId());
        var paymentResponse = paymentClient.getPaymentByOrderId(orderEntity.getId());
        return ORDER_MAPPER.buildOrderResponse(orderEntity, productResponse,paymentResponse);

    }
}
