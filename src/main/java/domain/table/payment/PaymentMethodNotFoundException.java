package domain.table.payment;

public class PaymentMethodNotFoundException extends RuntimeException {
    public PaymentMethodNotFoundException(final String message) {
        super(message);
    }
}
