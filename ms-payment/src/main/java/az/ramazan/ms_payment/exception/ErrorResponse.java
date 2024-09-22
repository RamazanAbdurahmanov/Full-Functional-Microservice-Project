package az.ramazan.ms_payment.exception;

import lombok.Builder;

@Builder
public record ErrorResponse(String message) {
}
