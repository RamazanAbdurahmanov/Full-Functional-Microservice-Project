package az.ramazan.ms_order.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessage {
    ORDER_NOT_FOUND("Order not found with id: %s");
    private final String message;
}
