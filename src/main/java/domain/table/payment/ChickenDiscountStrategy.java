package domain.table.payment;

public class ChickenDiscountStrategy implements DiscountStrategy {
    private static final int DISCOUNT_UNIT = 10;
    private static final int DISCOUNT_PRICE_PER_UNIT = 10000;

    private final long discountMoney;

    public ChickenDiscountStrategy(final long chickenCount) {
        this.discountMoney = chickenCount / DISCOUNT_UNIT * DISCOUNT_PRICE_PER_UNIT;
    }

    @Override
    public long discount(final long money) {
        return money - discountMoney;
    }
}
