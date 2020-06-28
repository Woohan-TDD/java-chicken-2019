package view;

import domain.menu.Menu;
import domain.table.order.Orders;
import domain.table.Table;

import java.util.List;

public class OutputView {
    private static final String TOP_LINE = "┌ ─ ┐";
    private static final String TABLE_FORMAT = "| %s |";
    private static final String BOTTOM_LINE = "└ ─ ┘";
    private static final String ORDER_BOTTOM_LINE = "└ ₩ ┘";

    public static void printMainScreen() {
        System.out.println("## 메인화면");
        System.out.println("1 - 주문등록");
        System.out.println("2 - 결제하기");
        System.out.println("3 - 프로그램 종료");
    }

    public static void printTables(final List<Table> tables) {
        System.out.println("## 테이블 목록");
        final int size = tables.size();
        printTopLine(size);
        printTableNumbers(tables);
        printBottomLine(tables);
    }

    public static void printMenus(final List<Menu> menus) {
        for (final Menu menu : menus) {
            System.out.println(menu);
        }
    }

    private static void printTopLine(final int count) {
        for (int index = 0; index < count; index++) {
            System.out.print(TOP_LINE);
        }
        System.out.println();
    }

    private static void printTableNumbers(final List<Table> tables) {
        for (final Table table : tables) {
            System.out.printf(TABLE_FORMAT, table);
        }
        System.out.println();
    }

    private static void printBottomLine(final List<Table> tables) {
        for (Table table : tables) {
            printCheckOrder(table);
        }
        System.out.println();
    }

    private static void printCheckOrder(final Table table) {
        if (table.hasOrder()) {
            System.out.print(ORDER_BOTTOM_LINE);
            return;
        }
        System.out.print(BOTTOM_LINE);
    }

    public static void printOrderHistory(final Orders orders) {
        System.out.println("## 주문 내역");
        System.out.println("메뉴 수량 금액");
        for (Menu menu : orders.getMenus()) {
            System.out.print(menu.getName() + " ");
            System.out.print(orders.getCount(menu) + " ");
            System.out.println(orders.getPrice(menu));
        }
    }

    public static void printTablePayment(final Table table) {
        System.out.println("## " + table + "번 테이블의 결제를 진행합니다.");
    }

    public static void printFinalAmount(final long payment) {
        System.out.println("## 최종 결제할 금액");
        System.out.println(payment + "원");
    }
}