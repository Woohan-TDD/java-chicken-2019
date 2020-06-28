package domain.menu;

import domain.order.Amount;

public class Menu {
	private final int number;
	private final String name;
	private final Category category;
	private final int price;

	public Menu(final int number, final String name, final Category category, final int price) {
		this.number = number;
		this.name = name;
		this.category = category;
		this.price = price;
	}

	public boolean isSameNumber(int other) {
		return number == other;
	}

	public boolean isSameCategory(Category other) {
		return category == other;
	}

	public int calculateMultiplePrice(Amount amount) {
		return amount.multiply(price);
	}

	public int getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public String getCategoryName() {
		return category.getName();
	}

	public int getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return category + " " + number + " - " + name + " : " + price + "원";
	}
}
