package view;

import java.util.List;

import domain.menu.Menu;
import domain.table.Table;
import domain.table.order.Order;
import domain.table.order.Orders;

public class OutputView {
    private static final String TOP_LINE = "┌ ─ ┐";
    private static final String TABLE_FORMAT = "| %s |";
    private static final String EMPTY_TABLE_BOTTOM_LINE = "└ ─ ┘";
    private static final String PAYABLE_BOTTOM_LINE = "└ ₩ ┘";

    public void printFeatures() {
        System.out.println();
        System.out.println("## 메인화면");
        System.out.println("1 - 주문등록");
        System.out.println("2 - 결제하기");
        System.out.println("3 - 프로그램 종료");
    }

    public void printUnknownFeature(final String message) {
        System.out.println();
        System.out.println(message);
    }

    public void printTables(final List<Table> tables) {
        System.out.println();
        System.out.println("## 테이블 목록");
        final int size = tables.size();
        printLine(TOP_LINE, size);
        printTableNumbers(tables);
        printBottomLine(tables);
    }

    public void printMenus(final List<Menu> menus) {
        System.out.println();
        System.out.println("## 메뉴 목록");
        for (final Menu menu : menus) {
            System.out.println(menu);
        }
    }

    public void printOrders(final Table table) {
        System.out.println();
        System.out.println("## 주문 내역");
        System.out.println("메뉴 수량 금액");
        Orders orders = table.getOrders();
        for (Order order : orders.getOrders()) {
            System.out.println(order);
        }
    }

    public void printExitMessage() {
        System.out.println();
        System.out.println("## 프로그램을 종료합니다.");
    }

    private void printLine(final String line, final int count) {
        for (int index = 0; index < count; index++) {
            System.out.print(line);
        }
        System.out.println();
    }

    private void printBottomLine(final List<Table> tables) {
        for (Table table : tables) {
            if (table.hasOrder()) {
                System.out.print(PAYABLE_BOTTOM_LINE);
                continue;
            }
            System.out.print(EMPTY_TABLE_BOTTOM_LINE);
        }
    }

    private void printTableNumbers(final List<Table> tables) {
        for (final Table table : tables) {
            System.out.printf(TABLE_FORMAT, table);
        }
        System.out.println();
    }

    public void printTotalPrice(final long totalPrice) {
        System.out.println();
        System.out.println("## 최종 결제할 금액");
        System.out.println(totalPrice + "원");
    }

    public void printWrongInputMessage() {
        System.out.println();
        System.out.println("## 잘못된 입력입니다. 다시 시도하세요.");
    }
}
