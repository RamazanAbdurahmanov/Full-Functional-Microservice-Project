package az.ramazan.ms_order.exception;

import lombok.Builder;

@Builder
public record ErrorResponse(String message) {
}
