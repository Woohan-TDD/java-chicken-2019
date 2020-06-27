package view;

import java.util.Collection;
import java.util.Map;

import domain.table.Table;
import domain.table.Tables;
import domain.table.order.OrderCount;
import domain.table.order.menu.Menu;
import domain.table.order.menu.Menus;

public class OutputView {
	private static final String TOP_LINE = "┌ ─ ┐";
	private static final String TABLE_FORMAT = "| %s |";
	private static final String BOTTOM_LINE = "└ ─ ┘";

	public void printMain() {
		System.out.println("## 메인화면");
		System.out.println("1 - 주문등록");
		System.out.println("2 - 결제하기");
		System.out.println("3 - 프로그램 종료");
	}

	public void printTables(final Tables tables) {
		System.out.println("## 테이블 목록");
		final int size = tables.getTableById().size();
		printLine(TOP_LINE, size);
		printTableNumbers(tables.getTableById().values());
		printLine(BOTTOM_LINE, size);
	}

	public void printMenus(final Menus menus) {
		Map<Integer, Menu> menuById = menus.getMenuById();
		for (final Menu menu : menuById.values()) {
			System.out.println(menu);
		}
	}

	private void printLine(final String line, final int count) {
		for (int index = 0; index < count; index++) {
			System.out.print(line);
		}
		System.out.println();
	}

	private void printTableNumbers(final Collection<Table> tables) {
		for (final Table table : tables) {
			System.out.printf(TABLE_FORMAT, table);
		}
		System.out.println();
	}

	public void printOrderHistory(Table table) {
		System.out.println("## 주문내역");
		System.out.println("메뉴 수량 금액");

		Map<Menu, OrderCount> menuByCount = table.getOrder().getOrderByCount();
		for (Menu menu : menuByCount.keySet()) {
			System.out.printf("%s %d %d\n", menu.getName(), menuByCount.get(menu).getAmount(), menu.getPrice());
		}
	}

	public void printPrice(long price) {
		System.out.printf("%d원\n", price);
	}
}
