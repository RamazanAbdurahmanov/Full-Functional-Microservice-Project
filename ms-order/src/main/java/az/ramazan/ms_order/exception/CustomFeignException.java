package az.ramazan.ms_order.exception;

public class CustomFeignException extends RuntimeException{
    public CustomFeignException(String message) {
        super(message);
    }
}
