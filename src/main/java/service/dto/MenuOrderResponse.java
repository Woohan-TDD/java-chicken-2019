package service.dto;

import java.util.List;
import java.util.stream.Collectors;

import domain.order.Order;

public class MenuOrderResponse {
	private String menuName;
	private int amount;
	private int price;

	private MenuOrderResponse(String menuName, int amount, int price) {
		this.menuName = menuName;
		this.amount = amount;
		this.price = price;
	}

	public static List<MenuOrderResponse> listOf(Order order) {
		return order.getMenuOrders().entrySet()
			.stream()
			.map(entry -> new MenuOrderResponse(entry.getKey().getName(), entry.getValue().getAmount(),
				entry.getKey().getPrice()))
			.collect(Collectors.toList());
	}

	public String getMenuName() {
		return menuName;
	}

	public int getAmount() {
		return amount;
	}

	public int getPrice() {
		return price;
	}
}
