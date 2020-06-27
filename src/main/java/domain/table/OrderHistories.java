package domain.table;

import java.util.Collections;
import java.util.List;

import domain.menu.Category;

public class OrderHistories {

	private static final int MAX_MENU_COUNT = 99;

	private final List<OrderHistory> orderHistories;

	public OrderHistories(final List<OrderHistory> orderHistories) {
		this.orderHistories = orderHistories;
	}

	public void add(OrderHistory orderHistory) {
		validateMenuQuantityGreaterThanNinetyNine(orderHistory);
		orderHistories.add(orderHistory);
	}

	public int getChickenCount() {
		return orderHistories.stream()
			.filter(orderHistory -> orderHistory.isSameCategory(Category.CHICKEN))
			.map(OrderHistory::getQuantity)
			.mapToInt(n -> n)
			.sum();
	}

	public int size() {
		return orderHistories.size();
	}

	public void clear() {
		orderHistories.clear();
	}

	private void validateMenuQuantityGreaterThanNinetyNine(OrderHistory orderHistory) {
		long orderedMenuCount = orderHistories.stream()
			.filter(orderHistoryValue -> orderHistoryValue.isSameMenu(orderHistory.getMenu()))
			.count();
		long totalMenuCount = orderedMenuCount + orderHistory.getQuantity();

		if(totalMenuCount >= MAX_MENU_COUNT){
			throw new IllegalArgumentException("한 메뉴당 99까지만 주문할 수 있습니다. menu = " + orderHistory.getMenu());
		}
	}

	public List<OrderHistory> getOrderHistories() {
		return Collections.unmodifiableList(orderHistories);
	}
}
