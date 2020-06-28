package service.dto;

import java.util.List;

public class TableOrderResponse {
	private int tableNumber;
	private List<MenuOrderResponse> menuOrderResponses;

	public TableOrderResponse(int tableNumber, List<MenuOrderResponse> menuOrderResponses) {
		this.tableNumber = tableNumber;
		this.menuOrderResponses = menuOrderResponses;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public List<MenuOrderResponse> getMenuOrderResponses() {
		return menuOrderResponses;
	}
}
