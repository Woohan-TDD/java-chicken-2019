package domain.table;

import domain.menu.Category;
import domain.order.Order;

public enum OrderAmountDiscount implements OrderDiscountStrategy {
	TEN_CHICKEN_DISCOUNT(order -> (order.calculatePrice() -
		order.divideCategoryAmountByUnit(Category.CHICKEN, Constants.TEN_CHICKEN) * Constants.TEN_CHICKEN_DISCOUNT));

	private final OrderDiscountStrategy orderDiscountStrategy;

	OrderAmountDiscount(OrderDiscountStrategy orderDiscountStrategy) {
		this.orderDiscountStrategy = orderDiscountStrategy;
	}

	@Override
	public int calculate(Order order) {
		return orderDiscountStrategy.calculate(order);
	}

	private static class Constants {
		private static final int TEN_CHICKEN_DISCOUNT = 10_000;
		private static final int TEN_CHICKEN = 10;
	}
}
