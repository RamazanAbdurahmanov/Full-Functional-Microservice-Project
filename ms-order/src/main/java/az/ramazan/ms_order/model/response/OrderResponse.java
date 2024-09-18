package az.ramazan.ms_order.model.response;

import az.ramazan.ms_order.model.client.response.ProductResponse;
import az.ramazan.ms_order.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private Long id;
    private Long productId;
    private Integer quantity;
    private OrderStatus status;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    private ProductResponse product;
}
