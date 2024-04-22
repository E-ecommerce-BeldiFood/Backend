package ma.beldifood.productcatalogservice.exception;

public class EmptyEntryException extends RuntimeException {
    public EmptyEntryException(String message) {
        super(message);
    }
}
