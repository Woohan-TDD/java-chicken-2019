package service.dto;

public class TableOrderRequest {
	private int tableNumber;
	private int menuNumber;
	private int menuAmount;

	public TableOrderRequest(int tableNumber, int menuNumber, int menuAmount) {
		this.tableNumber = tableNumber;
		this.menuNumber = menuNumber;
		this.menuAmount = menuAmount;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public int getMenuNumber() {
		return menuNumber;
	}

	public int getMenuAmount() {
		return menuAmount;
	}
}
