package view;

import domain.Menu;
import domain.Order;
import domain.Orders;
import domain.Table;

import java.util.List;

public class OutputView {
    private static final String TOP_LINE = "┌ ─ ┐";
    private static final String TABLE_FORMAT = "| %s |";
    private static final String BOTTOM_LINE = "└ ─ ┘";
    private static final String BOTTOM_ORDERED_LINE = "└ ₩ ┘";

    public static void printTables(final List<Table> tables) {
        System.out.println("## 테이블 목록");
        final int size = tables.size();
        printLine(TOP_LINE, size);
        printTableNumbers(tables);
        printBottomLines(tables);
        System.out.println();
    }

    private static void printBottomLines(List<Table> tables) {
        for (Table table : tables) {
            printBottomLine(table);
        }
    }

    private static void printBottomLine(Table table) {
        if (table.isOrderEmpty()) {
            System.out.print(BOTTOM_LINE);
            return;
        }
        System.out.print(BOTTOM_ORDERED_LINE);
    }

    public static void printMenus(final List<Menu> menus) {
        for (final Menu menu : menus) {
            System.out.println(menu);
        }
    }

    private static void printLine(final String line, final int count) {
        for (int index = 0; index < count; index++) {
            System.out.print(line);
        }
        System.out.println();
    }

    private static void printTableNumbers(final List<Table> tables) {
        for (final Table table : tables) {
            System.out.printf(TABLE_FORMAT, table);
        }
        System.out.println();
    }

    public static void printMain() {
        System.out.println("## 메인화면");
        System.out.println("1 - 주문등록");
        System.out.println("2 - 결제하기");
        System.out.println("3 - 프로그램 종료");
    }

    public static void printExit() {
        System.out.println("프로그램을 종료합니다.");
    }

    public static void printError(String message) {
        System.out.println(message);
    }

    public static void printOrderedMenus(Table table) {
        System.out.println("## 주문내역");
        System.out.println("메뉴\t수량\t금액");
        Orders orders = table.getOrders();
        for (Order order : orders.getOrders()) {
            System.out.printf("%s\t%d\t%d\n", order.getMenu().getName(), order.getCountNumber(), order.getTotalPrice());
        }
    }

    public static void printTotalPrice(double price) {
        System.out.println("## 최종 결제금액\n" + price + "원");
    }
}
