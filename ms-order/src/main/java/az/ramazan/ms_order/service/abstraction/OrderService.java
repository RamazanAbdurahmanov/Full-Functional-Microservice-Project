package az.ramazan.ms_order.service.abstraction;

import az.ramazan.ms_order.model.request.CreateOrderRequest;
import az.ramazan.ms_order.model.response.OrderResponse;

public interface OrderService {
    void createOrder(CreateOrderRequest createOrderRequest);
    OrderResponse getOrderById(Long id);


}
