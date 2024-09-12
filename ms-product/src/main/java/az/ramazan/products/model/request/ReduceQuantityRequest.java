package az.ramazan.products.model.request;

import az.ramazan.products.model.constants.ApplicationConstants;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static az.ramazan.products.model.constants.ApplicationConstants.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReduceQuantityRequest {
    @NotNull(message = PRICE_IS_REQUIRED)
    private Long productId;
    @NotNull(message = QUANTITY_IS_REQUIRED)
    private Integer quantity;
}
