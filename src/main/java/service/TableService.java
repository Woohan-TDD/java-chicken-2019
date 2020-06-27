package service;

import java.util.List;

import domain.Menu;
import domain.MenuRepository;
import domain.OrderHistory;
import domain.Table;
import domain.TableRepository;

public class TableService {
	private TableRepository tableRepository;
	private MenuRepository menuRepository;

	public TableService(final TableRepository tableRepository, final MenuRepository menuRepository) {
		this.tableRepository = tableRepository;
		this.menuRepository = menuRepository;
	}

	public Table findByNumber(int number) {
		return tableRepository.findByNumber(number);
	}

	public List<Table> findTables() {
		return tableRepository.findAll();
	}

	public void updateOrderHistoryByNumber(final int tableNumber, final int menuNumber, final int menuQuantity){
		Menu menu =menuRepository .findById(menuNumber);
		OrderHistory orderHistory = new OrderHistory(menu, menuQuantity);
		tableRepository.updateOrderHistoryByNumber(tableNumber, orderHistory);
	}
}
