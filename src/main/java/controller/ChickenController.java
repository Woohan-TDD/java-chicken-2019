package controller;

import service.MenuService;
import service.PaymentService;
import service.TableOrderService;
import service.dto.PayRequest;
import service.dto.TableOrderRequest;
import view.InputView;
import view.OutputView;

public class ChickenController {
	private final InputView inputView;
	private final OutputView outputView;
	private final MenuService menuService;
	private final PaymentService paymentService;
	private final TableOrderService tableOrderService;

	public ChickenController(InputView inputView, OutputView outputView, MenuService menuService,
		PaymentService paymentService, TableOrderService tableOrderService) {
		this.inputView = inputView;
		this.outputView = outputView;
		this.menuService = menuService;
		this.paymentService = paymentService;
		this.tableOrderService = tableOrderService;
	}

	public void order() {
		outputView.printTables(tableOrderService.findAllTables());
		int tableNumber = inputView.inputTableNumber();

		outputView.printMenus(menuService.findAllMenus());
		int menuNumber = inputView.inputMenuNumber();

		int amount = inputView.inputAmount();

		tableOrderService.addOrder(new TableOrderRequest(tableNumber, menuNumber, amount));
	}

	public void pay() {
		outputView.printTables(tableOrderService.findAllTables());
		int tableNumber = inputView.inputTableNumber();

		outputView.printOrder(tableOrderService.findTableOrder(tableNumber));
		int paymentNumber = inputView.inputPaymentNumber();

		outputView.printPaymentAmount(paymentService.payOrder(new PayRequest(tableNumber, paymentNumber)));
	}
}
