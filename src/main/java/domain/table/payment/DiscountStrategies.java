package domain.table.payment;

import java.util.List;

public class DiscountStrategies implements DiscountStrategy {
    private final List<DiscountStrategy> discountStrategies;

    public DiscountStrategies(final List<DiscountStrategy> discountStrategies) {
        this.discountStrategies = discountStrategies;
    }

    @Override
    public long discount(long money) {
        for (DiscountStrategy discountStrategy : discountStrategies) {
            money = discountStrategy.discount(money);
        }
        return money;
    }
}
