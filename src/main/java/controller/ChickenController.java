package controller;

import domain.menu.Menu;
import domain.menu.MenuRepository;
import domain.menu.Menus;
import domain.table.Table;
import domain.table.TableRepository;
import domain.table.Tables;
import domain.type.FunctionType;
import domain.type.PaymentType;
import view.InputView;
import view.OutputView;

public class ChickenController {

    private static final ChickenController chickenController = new ChickenController();

    private ChickenController() {}

    public static ChickenController getInstance() {
        return chickenController;
    }

    public void execute() {
        try {
            work();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    private void work() {
        Tables tables = Tables.from(TableRepository.tables());
        Menus menus = Menus.from(MenuRepository.menus());
        FunctionType functionType;

        do {
            OutputView.printMainScreen();
            functionType = FunctionType.find(InputView.inputFunctionNumber());
            function(functionType, tables, menus);
        } while(functionType.isNotThree());
    }

    private void function(final FunctionType functionType,  final Tables tables, final Menus menus) {
        if (functionType == FunctionType.ORDER) {
            addOrder(tables, menus);
        }
        if (functionType == FunctionType.PAYMENT) {
            countOrder(tables);
        }
    }

    private void addOrder(final Tables tables, final Menus menus) {
        OutputView.printTables(tables.getTables());
        Table table = tables.findTableByNumber(InputView.inputTableNumber());
        OutputView.printMenus(menus.getMenus());
        Menu menu = menus.findMenuByNumber(InputView.inputMenuNumber());
        table.addOrder(menu, InputView.inputMenuCount());
    }

    private void countOrder(final Tables tables) {
        OutputView.printTables(tables.getTables());
        Table table = tables.findTableByNumber(InputView.inputTableNumber());
        if (!table.hasOrder()) {
            throw new IllegalArgumentException("주문이 없는 테이블입니다.");
        }
        OutputView.printOrderHistory(table.getOrders());
        OutputView.printTablePayment(table);
        PaymentType paymentType = PaymentType.find(InputView.inputPayment());
        OutputView.printFinalAmount(paymentType.payment(table.calculateTotalPrice()));
        table.resetOrders();
    }
}