package domain.payment;

import domain.table.order.Order;

public class ChickenDiscountStrategy implements DiscountStrategy {
	private static final int DISCOUNT_PRICE = 10000;

	@Override
	public long calculate(long money, Order order) {
		int count = order.calculateChickenCount();
		int discount = (count / 10) * DISCOUNT_PRICE;
		return discount;
	}
}
