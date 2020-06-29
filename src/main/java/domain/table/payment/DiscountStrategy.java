package domain.table.payment;

@FunctionalInterface
public interface DiscountStrategy {
    long discount(final long money);
}
