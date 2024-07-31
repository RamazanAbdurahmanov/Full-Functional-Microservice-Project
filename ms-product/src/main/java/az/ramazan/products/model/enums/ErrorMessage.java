package az.ramazan.products.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessage {
    PRODUCT_NOT_FOUND("Product not found with id: %s");
    private final String message;
}
