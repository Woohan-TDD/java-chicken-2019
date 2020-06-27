package domain.payment;

import domain.table.order.Order;

public interface DiscountStrategy {
	long calculate(long money, Order order);
}
