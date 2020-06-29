package controller;

import java.util.List;

import domain.menu.Menu;
import domain.table.Table;
import domain.table.order.OrderAmount;
import domain.table.order.OrderNotFoundException;
import domain.table.payment.PaymentMethod;
import service.MenuService;
import service.TableService;
import view.InputView;
import view.OutputView;

public class OrderController {
    private final TableService tableService;
    private final MenuService menuService;
    private final InputView inputView;
    private final OutputView outputView;

    public OrderController(final TableService tableService, final MenuService menuService, final InputView inputView,
            final OutputView outputView) {
        this.tableService = tableService;
        this.menuService = menuService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void createOrUpdateOrder() {
        int tableNumber = getTableNumber(false);
        int menuNumber = getMenuNumber();
        int orderAmount = getOrderAmount();

        tableService.addOrder(tableNumber, menuNumber, orderAmount);
    }

    public void deleteOrder() {
        int tableNumber = getTableNumber(true);
        int paymentType = getPaymentType();

        long totalPrice = tableService.payment(tableNumber, paymentType);
        outputView.printTotalPrice(totalPrice);
    }

    private int getTableNumber(final boolean needPrintOrders) {
        List<Table> tables = tableService.getTables();
        outputView.printTables(tables);

        int tableNumber = inputView.inputTableNumber();
        Table table = tableService.getTableByNumber(tableNumber);

        if (needPrintOrders) {
            validateOrders(table);
            outputView.printOrders(table);
        }
        return tableNumber;
    }

    private int getMenuNumber() {
        List<Menu> menus = menuService.getMenus();
        outputView.printMenus(menus);

        int menuNumber = inputView.inputMenuNumber();
        menuService.getMenuByNumber(menuNumber);
        return menuNumber;
    }

    private void validateOrders(final Table table) {
        if (!table.hasOrder()) {
            throw new OrderNotFoundException("주문 내역이 존재하지 않습니다.\n");
        }
    }

    private int getPaymentType() {
        int paymentType = inputView.inputPaymentType();
        PaymentMethod.ofNumber(paymentType);
        return paymentType;
    }

    private int getOrderAmount() {
        int orderAmount = inputView.inputOrderAmount();
        new OrderAmount(orderAmount);
        return orderAmount;
    }
}
