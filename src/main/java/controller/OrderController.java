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

    public void addOrderToTable() {
        Table table = getTable();
        Menu menu = getMenu();
        int orderAmount = getOrderAmount();

        tableService.addOrder(table.getNumber(), menu.getNumber(), orderAmount);
    }

    public void paymentTable() {
        Table table = getTable();
        validateOrders(table);
        outputView.printOrders(table);

        int paymentType = getPaymentType();

        long totalPrice = tableService.payment(table.getNumber(), paymentType);
        outputView.printTotalPrice(totalPrice);
    }

    private Table getTable() {
        List<Table> tables = tableService.getTables();
        outputView.printTables(tables);

        int tableNumber = inputView.inputTableNumber();
        return tableService.getTableByNumber(tableNumber);
    }

    private Menu getMenu() {
        List<Menu> menus = menuService.getMenus();
        outputView.printMenus(menus);

        int menuNumber = inputView.inputMenuNumber();
        return menuService.getMenuByNumber(menuNumber);
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

    private void validateOrders(final Table table) {
        if (!table.hasOrder()) {
            throw new OrderNotFoundException("주문 내역이 존재하지 않습니다.\n");
        }
    }
}
