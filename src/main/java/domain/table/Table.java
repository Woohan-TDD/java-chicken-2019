package domain.table;

import java.util.List;

public class Table {

	private static final int ZERO_ORDER_HISTORIES = 0;

	private final int number;
	private final OrderHistories orderHistories;

	public Table(final int number, final List<OrderHistory> orderHistories) {
		this.number = number;
		this.orderHistories = new OrderHistories(orderHistories);
	}

	public boolean isSameNumber(final int tableNumber) {
		return this.number == tableNumber;
	}

	public void addOrderHistory(final OrderHistory orderHistory) {
		orderHistories.add(orderHistory);
	}

	public boolean isUsingTable() {
		return orderHistories.size() != ZERO_ORDER_HISTORIES;
	}

	public void clearOrderHistories(){
		orderHistories.clear();
	}

	@Override
	public String toString() {
		return Integer.toString(number);
	}

	public int getNumber() {
		return number;
	}

	public OrderHistories getOrderHistories() {
		return orderHistories;
	}
}
