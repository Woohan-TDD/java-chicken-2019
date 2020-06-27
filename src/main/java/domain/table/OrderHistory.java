package domain.table;

import domain.menu.Category;
import domain.menu.Menu;

public class OrderHistory {
	private final Menu menu;
	private final int quantity;

	public OrderHistory(final Menu menu, final int quantity) {
		this.menu = menu;
		this.quantity = quantity;
	}

	public long calculatePaymentAmount() {
		return menu.getPrice() * quantity;
	}

	public boolean isSameCategory(final Category category) {
		return menu.getCategory() == category;
	}

	public boolean isSameMenu(final Menu menu) {
		return this.menu == menu;
	}

	@Override
	public String toString() {
		return menu.getName() + " " + quantity + " " + (menu.getPrice() * quantity);
	}

	public Menu getMenu() {
		return menu;
	}

	public int getQuantity() {
		return quantity;
	}
}
