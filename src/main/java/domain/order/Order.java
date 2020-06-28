package domain.order;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import domain.menu.Category;
import domain.menu.Menu;

public class Order {
	private final Map<Menu, Amount> menuOrders;

	public Order(Map<Menu, Amount> menuOrders) {
		Objects.requireNonNull(menuOrders, "값이 존재하지 않습니다.");
		this.menuOrders = menuOrders;
	}

	public static Order empty() {
		return new Order(new HashMap<>());
	}

	public int calculatePrice() {
		return menuOrders.entrySet().stream()
			.mapToInt(entry -> entry.getKey().calculateMultiplePrice(entry.getValue()))
			.sum();
	}

	public void addMenu(Menu menu, Amount amount) {
		menuOrders.merge(menu, amount, Amount::sum);
	}

	public boolean isEmpty() {
		return menuOrders.isEmpty();
	}

	public void clear() {
		menuOrders.clear();
	}

	public int divideCategoryAmountByUnit(Category category, int unit) {
		if (unit < 0) {
			throw new IllegalArgumentException("unit의 단위는 0보다 커야 합니다. unit : " + unit);
		}
		return countAmountByCategory(category) / unit;
	}

	private int countAmountByCategory(Category category) {
		return menuOrders.keySet().stream()
			.filter(menu -> menu.isSameCategory(category))
			.map(menuOrders::get)
			.reduce(0, (subTotal, amount) -> amount.calculateSum(subTotal), Integer::sum);
	}

	public Map<Menu, Amount> getMenuOrders() {
		return Collections.unmodifiableMap(menuOrders);
	}
}
