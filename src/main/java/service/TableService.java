package service;

import java.math.BigDecimal;
import java.util.List;

import domain.menu.Menu;
import domain.menu.MenuRepository;
import domain.payment.Payment;
import domain.table.OrderHistory;
import domain.table.Table;
import domain.table.TableRepository;

public class TableService {

	private final TableRepository tableRepository;
	private final MenuRepository menuRepository;

	public TableService(final TableRepository tableRepository, final MenuRepository menuRepository) {
		this.tableRepository = tableRepository;
		this.menuRepository = menuRepository;
	}

	public Table findByNumber(final int number) {
		return tableRepository.findByNumber(number)
			.orElseThrow(() -> new IllegalArgumentException("해당 테이블을 찾을 수 없습니다. number = " + number));
	}

	public List<Table> findTables() {
		return tableRepository.findAll();
	}

	public void updateOrderHistoryByNumber(final int tableNumber, final int menuNumber, final int menuQuantity) {
		Menu menu = menuRepository.findById(menuNumber);
		OrderHistory orderHistory = new OrderHistory(menu, menuQuantity);
		tableRepository.updateOrderHistoryByNumber(tableNumber, orderHistory);
	}

	public void deleteOrderHistoriesByNumber(final int number) {
		Table table = tableRepository.findByNumber(number)
			.orElseThrow(() -> new IllegalArgumentException("해당 테이블을 찾을 수 없습니다. number = " + number));
		table.clearOrderHistories();
	}

	public BigDecimal calculatePayment(final int tableNumber, final int paymentNumber) {
		Table table = tableRepository.findByNumber(tableNumber)
			.orElseThrow(() -> new IllegalArgumentException("해당 테이블을 찾을 수 없습니다. number = " + tableNumber));
		Payment payment = Payment.of(paymentNumber);
		table.clearOrderHistories();
		return payment.pay(table.getOrderHistories());
	}
}
