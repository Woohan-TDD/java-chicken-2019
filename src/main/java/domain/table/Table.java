package domain.table;

import java.util.List;

public class Table {
	private final int number;
	private final OrderHistories orderHistories;

	public Table(final int number, List<OrderHistory> orderHistories) {
		this.number = number;
		this.orderHistories = new OrderHistories(orderHistories);
	}

	public boolean isSameNumber(final int tableNumber) {
		return this.number == tableNumber;
	}

	public void addOrderHistory(OrderHistory orderHistory) {
		orderHistories.add(orderHistory);
	}

	public boolean isUsingTable() {
		return orderHistories.size() != 0;
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
