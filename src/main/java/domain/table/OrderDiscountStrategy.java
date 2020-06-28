package domain.table;

import domain.order.Order;

public interface OrderDiscountStrategy {
	int calculate(Order order);
}
