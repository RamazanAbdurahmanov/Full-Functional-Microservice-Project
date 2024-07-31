package az.ramazan.products.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String messaage) {
        super(messaage);
    }
}
