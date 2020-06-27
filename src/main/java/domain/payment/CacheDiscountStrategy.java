package domain.payment;

import domain.table.order.Order;

public class CacheDiscountStrategy implements DiscountStrategy {
	private static final double DISCOUNT_RATE = 0.95;

	@Override
	public long calculate(long money, Order order) {
		return (long)((double)money * DISCOUNT_RATE);
	}
}
