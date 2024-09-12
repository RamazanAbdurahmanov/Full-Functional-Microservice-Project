package az.ramazan.ms_order.service.concrete;

import az.ramazan.ms_order.dao.repository.OrderRepository;
import az.ramazan.ms_order.exception.NotFoundException;
import az.ramazan.ms_order.model.request.CreateOrderRequest;
import az.ramazan.ms_order.model.response.OrderResponse;
import az.ramazan.ms_order.service.abstraction.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static az.ramazan.ms_order.mapper.OrderMapper.ORDER_MAPPER;
import static az.ramazan.ms_order.model.enums.ErrorMessage.*;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class OrderServiceHandler implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public void createOrder(CreateOrderRequest createOrderRequest) {
        orderRepository.save(ORDER_MAPPER.buildOrderEntity(createOrderRequest));


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
