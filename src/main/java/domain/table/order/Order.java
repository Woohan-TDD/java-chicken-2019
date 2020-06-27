package domain.table.order;

import java.util.HashMap;
import java.util.Map;

import domain.table.order.menu.Menu;

public class Order {
	private final Map<Menu, OrderCount> orderByCount;

	public Order() {
		this.orderByCount = new HashMap<>();
	}

	public void add(Menu menu, OrderCount orderCount) {
		int weight = 0;

		if (orderByCount.containsKey(menu)) {
			weight = orderByCount.get(menu).getAmount();
		}

		orderByCount.put(menu, orderCount.add(weight));
	}

	public void clear() {
		orderByCount.clear();
	}

	public boolean isEmpty() {
		return orderByCount.isEmpty();
	}

	public long calculateTotalMoney() {
		long sum = 0;

		for (Menu menu : orderByCount.keySet()) {
			OrderCount orderCount = orderByCount.get(menu);
			sum += menu.calculatePrice(orderCount);
		}

		return sum;
	}

	public int calculateChickenCount() {
		int sum = 0;
		for (Menu menu : orderByCount.keySet()) {
			if (menu.isChicken()) {
				sum += orderByCount.get(menu).getAmount();
			}
		}
		return sum;
	}

	public Map<Menu, OrderCount> getOrderByCount() {
		return orderByCount;
	}
}
