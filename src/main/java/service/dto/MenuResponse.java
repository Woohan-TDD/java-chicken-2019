package service.dto;

import java.util.List;
import java.util.stream.Collectors;

import domain.menu.Menu;

public class MenuResponse {
	private String categoryName;
	private int number;
	private String name;
	private int price;

	private MenuResponse(String categoryName, int number, String name, int price) {
		this.categoryName = categoryName;
		this.number = number;
		this.name = name;
		this.price = price;
	}

	public static List<MenuResponse> listOf(List<Menu> menus) {
		return menus.stream()
			.map(MenuResponse::of)
			.collect(Collectors.toList());
	}

	private static MenuResponse of(Menu menu) {
		return new MenuResponse(menu.getCategoryName(), menu.getNumber(), menu.getName(), menu.getPrice());
	}

	public String getCategoryName() {
		return categoryName;
	}

	public int getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}
}
