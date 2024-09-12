package az.ramazan.ms_order.model.request;

import az.ramazan.ms_order.model.enums.PaymentType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static az.ramazan.ms_order.model.constants.ApplicationConstants.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderRequest {
    @NotNull(message = PRODUCT_ID_IS_REQUIRED)
    private Long productId;

    @NotNull(message = QUANTITY_IS_REQUIRED)
    private Integer quantity;

    @NotNull(message = AMOUNT_IS_REQUIRED)
    private BigDecimal amount;
    private PaymentType paymentType;
}
