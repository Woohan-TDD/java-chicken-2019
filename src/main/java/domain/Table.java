package domain;

import java.util.List;

public class Table {
	private final int number;
	private final List<OrderHistory> orderHistories;

	public Table(final int number, final List<OrderHistory> orderHistories) {
		this.number = number;
		this.orderHistories = orderHistories;
	}

	public boolean isSameNumber(final int tableNumber) {
		return this.number == tableNumber;
	}

	public void addOrderHistory(OrderHistory orderHistory) {
		orderHistories.add(orderHistory);
	}

	@Override
	public String toString() {
		return Integer.toString(number);
	}

	public int getNumber() {
		return number;
	}

	public List<OrderHistory> getOrderHistories() {
		return orderHistories;
	}
}
