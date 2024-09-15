package az.ramazan.ms_order.service.concrete;

import az.ramazan.ms_order.client.ProductClient;
import az.ramazan.ms_order.dao.repository.OrderRepository;
import az.ramazan.ms_order.exception.NotFoundException;
import az.ramazan.ms_order.model.client.request.ReduceQuantityRequest;
import az.ramazan.ms_order.model.request.CreateOrderRequest;
import az.ramazan.ms_order.model.response.OrderResponse;
import az.ramazan.ms_order.service.abstraction.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import static az.ramazan.ms_order.mapper.OrderMapper.ORDER_MAPPER;
import static az.ramazan.ms_order.model.enums.ErrorMessage.*;
import static az.ramazan.ms_order.model.enums.OrderStatus.*;
import static java.lang.String.format;
import static java.math.BigDecimal.*;

@Service
@RequiredArgsConstructor
public class OrderServiceHandler implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    @Override
    @Transactional
    public void createOrder(CreateOrderRequest createOrderRequest) {
        var orderEntity = ORDER_MAPPER.buildOrderEntity(createOrderRequest);
        var productResponse = productClient.getProductById(createOrderRequest.getProductId());
        orderEntity.setAmount(productResponse.getPrice().multiply(valueOf(createOrderRequest.getQuantity())));
        var reduceQuantityRequest = new ReduceQuantityRequest(
                createOrderRequest.getProductId(),
                createOrderRequest.getQuantity()
        );
        orderRepository.save(orderEntity);
        try {
            productClient.reduceQuantity(reduceQuantityRequest);
            orderEntity.setStatus(APPROVED);
        } catch (Exception e) {
            orderEntity.setStatus(REJECTED);
        }



    }

    @Override
    public OrderResponse getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(ORDER_MAPPER::buildOrderResponse)
                .orElseThrow(() -> new NotFoundException(format(ORDER_NOT_FOUND.getMessage(),
                        id
                )));
    }
}
