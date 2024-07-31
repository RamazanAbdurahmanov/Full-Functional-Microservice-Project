package az.ramazan.products.model.request;

import az.ramazan.products.model.constants.ApplicationConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static az.ramazan.products.model.constants.ApplicationConstants.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductRequest {
    @NotBlank(message = NAME_IS_REQUIRED)
    private String name;
    @NotBlank(message = DESCRIPTION_IS_REQUIRED)
    private String description;
    @NotNull(message = PRICE_IS_REQUIRED)
    private BigDecimal price;
    @NotNull(message = QUANTITY_IS_REQUIRED)
    private Integer quantity;

}
