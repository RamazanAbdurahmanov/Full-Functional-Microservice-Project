package az.ramazan.ms_order.controller;

import az.ramazan.ms_order.exception.ServiceUnavailableException;
import az.ramazan.ms_order.model.request.CreateOrderRequest;
import az.ramazan.ms_order.model.response.OrderResponse;
import az.ramazan.ms_order.service.abstraction.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        orderService.createOrder(createOrderRequest);
    }
    @GetMapping("/{id}")
    @CircuitBreaker(name = "getOrderById",fallbackMethod="fallback")
    public OrderResponse getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }
    public OrderResponse fallback(Long response,Exception exception) {
        throw new ServiceUnavailableException("Service is currently unavailable.");
    }

}
