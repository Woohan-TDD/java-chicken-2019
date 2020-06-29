package domain.table.order;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(final String message) {
        super(message);
    }
}
