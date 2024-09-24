package az.ramazan.ms_order.exception;

import az.ramazan.ms_order.model.enums.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static az.ramazan.ms_order.model.enums.ErrorMessage.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handle(NotFoundException exception) {
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Exception exception) {
        return ErrorResponse.builder()
                .message(SERVER_ERROR.getMessage())
                .build();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }

    @ExceptionHandler(CustomFeignException.class)
    public ResponseEntity<ErrorResponse> handle(CustomFeignException exception) {
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(ErrorResponse.builder()
                        .message(exception.getMessage())
                        .build());

    }

}
