package domain.table;

import domain.table.order.Order;
import domain.table.order.OrderCount;
import domain.table.order.menu.Menu;

public class Table {
	private final int number;
	private final Order order;

	public Table(final int number, Order order) {
		this.number = number;
		this.order = order;
	}

	public void addOrder(Menu menu, OrderCount orderCount) {
		order.add(menu, orderCount);
	}

	@Override
	public String toString() {
		return Integer.toString(number);
	}

	public int getNumber() {
		return number;
	}

	public Order getOrder() {
		return order;
	}

	public void clear() {
		order.clear();
	}

	public boolean isEmpty() {
		return order.isEmpty();
	}
}
