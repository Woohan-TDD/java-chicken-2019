package controller;

import java.math.BigDecimal;

import domain.table.Table;
import service.TableService;
import view.InputView;
import view.OutputView;

public class PaymentController implements RunController {
	private final TableService tableService;

	public PaymentController(final TableService tableService) {
		this.tableService = tableService;
	}

	@Override
	public void run() {
		try {
			int tableNumber = getTableNumber();
			int paymentNumber = getPaymentNumber(tableService.findByNumber(tableNumber));
			BigDecimal account = tableService.calculatePayment(tableNumber, paymentNumber);
			OutputView.printResultAccount(account);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private int getTableNumber() {
		OutputView.printTables(tableService.findTables());
		return InputView.inputTableNumber();
	}

	private int getPaymentNumber(Table table) {
		OutputView.printOrderHistories(table.getOrderHistories());
		OutputView.printTablePaymentGuide(table.getNumber());
		return InputView.inputPaymentNumber();
	}
}
