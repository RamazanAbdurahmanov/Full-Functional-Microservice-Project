package az.ramazan.products.exception;

import lombok.Builder;

@Builder
public record ErrorResponse(String message) {
}
