package controller;

import domain.PosMenu;
import domain.payment.PaymentType;
import domain.table.Table;
import domain.table.TableRepository;
import domain.table.Tables;
import domain.table.order.OrderCount;
import domain.table.order.menu.Menu;
import domain.table.order.menu.MenuRepository;
import domain.table.order.menu.Menus;
import view.InputView;
import view.OutputView;

public class ChickenController {
	private final InputView inputView;
	private final OutputView outputView;

	public ChickenController(InputView inputView, OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void run() {
		outputView.printMain();

		Menus menus = new Menus(MenuRepository.menus());
		Tables tables = new Tables(TableRepository.tables());

		while (true) {
			int function = inputView.inputSelectFunction();
			PosMenu posMenu = PosMenu.of(function);
			if (PosMenu.ORDER == posMenu) {
				outputView.printTables(tables);
				Table table = tables.findTable(inputView.inputTableNumber());

				outputView.printMenus(menus);
				Menu menu = menus.findMenu(inputView.inputSelectMenu());

				OrderCount orderCount = OrderCount.of(inputView.inputMenuAmount());

				table.addOrder(menu, orderCount);
			} else if (PosMenu.PAYMENT == posMenu) {
				outputView.printTables(tables);
				Table table = tables.findOrderedTable(inputView.inputTableNumber());

				outputView.printOrderHistory(table);
				PaymentType paymentType = PaymentType.of(inputView.inputSelectPayment(table));

				long price = paymentType.calculatePrice(table.getOrder());
				outputView.printPrice(price);

				table.clear();
			} else if (PosMenu.EXIT == posMenu) {
				System.exit(0);
			}
		}
	}
}
