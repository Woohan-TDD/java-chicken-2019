package controller;

import java.math.BigDecimal;

import domain.payment.Payment;
import domain.table.Table;
import service.TableService;
import view.InputView;
import view.OutputView;

public class PaymentController implements RunController{
	private final TableService tableService;

	public PaymentController(final TableService tableService) {
		this.tableService = tableService;
	}

	@Override
	public void run() {
		Table table = tableService.findByNumber(getTableNumber());
		OutputView.printOrderHistories(table.getOrderHistories());
		OutputView.printTablePaymentGuide(table.getNumber());
		int paymentNumber = InputView.inputPaymentNumber();
		Payment payment = Payment.of(paymentNumber);
		BigDecimal account = payment.pay(table.getOrderHistories());
		OutputView.printResultAccount(account);
	}

	private int getTableNumber() {
		OutputView.printTables(tableService.findTables());
		return InputView.inputTableNumber();
	}
}
