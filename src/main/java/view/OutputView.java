package view;

import java.math.BigDecimal;
import java.util.List;

import domain.menu.Menu;
import domain.table.OrderHistories;
import domain.table.OrderHistory;
import domain.table.Table;

public class OutputView {

	private static final String TOP_LINE = "┌ ─ ┐";
	private static final String TABLE_FORMAT = "| %s |";
	private static final String BOTTOM_LINE_USING = "└ ₩ ┘";
	private static final String BOTTOM_LINE = "└ ─ ┘";

	public static void printMain() {
		System.out.println("## 메인화면");
		System.out.println("1 - 주문 등록");
		System.out.println("2 - 결제 하기");
		System.out.println("3 - 프로그램 종료");
		System.out.println();
	}

	public static void printTables(final List<Table> tables) {
		System.out.println();
		System.out.println("## 테이블 목록");
		printTopLines(tables.size());
		printTableNumbers(tables);
		printBottomLines(tables);
		System.out.println();
	}

	public static void printMenus(final List<Menu> menus) {
		for (final Menu menu : menus) {
			System.out.println(menu);
		}
		System.out.println();
	}

	private static void printTopLines(final int size) {
		for (int i = 0; i < size; i++) {
			System.out.print(TOP_LINE);
		}
		System.out.println();
	}

	private static void printBottomLines(final List<Table> tables) {
		for (Table table : tables) {
			printBottomLine(table);
		}
		System.out.println();
	}

	private static void printBottomLine(final Table table) {
		if (table.isUsingTable()) {
			System.out.print(BOTTOM_LINE_USING);
			return;
		}
		System.out.print(BOTTOM_LINE);
	}

	private static void printTableNumbers(final List<Table> tables) {
		for (final Table table : tables) {
			System.out.printf(TABLE_FORMAT, table);
		}
		System.out.println();
	}

	public static void printOrderHistories(final OrderHistories orderHistories) {
		System.out.println("## 주문 내역");
		System.out.println("메뉴 수량 금액");
		for (final OrderHistory orderHistory : orderHistories.getOrderHistories()) {
			System.out.println(orderHistory);
		}
		System.out.println();
	}

	public static void printTablePaymentGuide(final int number) {
		System.out.printf("## %d번 테이블의 결제를 진행합니다.", number);
	}

	public static void printResultAccount(final BigDecimal account) {
		System.out.println("## 최종 결제할 금액");
		System.out.println(account.toString() + "원");
		System.out.println();
	}
}
