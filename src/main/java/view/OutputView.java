package view;

import java.util.List;

import service.dto.MenuOrderResponse;
import service.dto.MenuResponse;
import service.dto.PayResponse;
import service.dto.TableOrderResponse;
import service.dto.TableResponse;

public class OutputView {
	private static final String TOP_LINE = "┌ ─ ┐";
	private static final String TABLE_FORMAT = "| %s |";
	private static final String BOTTOM_LINE = "└ ─ ┘";
	private static final String BOTTOM_LINE_ORDERED = "└ ₩ ┘";

	public void printTables(final List<TableResponse> tables) {
		System.out.println("## 테이블 목록");
		final int size = tables.size();
		printLine(size);
		printTableNumbers(tables);
		printBottomLine(tables);
	}

	public void printMenus(final List<MenuResponse> menus) {
		for (final MenuResponse menu : menus) {
			System.out.println(
				"[" + menu.getCategoryName() + "]" + " " + menu.getNumber() + " - " + menu.getName() + " : "
					+ menu.getPrice() + "원");
		}
	}

	private void printLine(final int count) {
		for (int index = 0; index < count; index++) {
			System.out.print(OutputView.TOP_LINE);
		}
		System.out.println();
	}

	private void printBottomLine(List<TableResponse> tableResponses) {
		for (TableResponse tableResponse : tableResponses) {
			printBottom(tableResponse);
		}
		System.out.println();
	}

	private void printBottom(TableResponse tableResponse) {
		if (tableResponse.isOrdering()) {
			System.out.print(BOTTOM_LINE_ORDERED);
			return;
		}
		System.out.print(BOTTOM_LINE);
	}

	private void printTableNumbers(final List<TableResponse> tables) {
		for (final TableResponse table : tables) {
			System.out.printf(TABLE_FORMAT, table.getNumber());
		}
		System.out.println();
	}

	public void printOrder(TableOrderResponse menuOrderResponses) {
		System.out.println("## 주문 내역");
		System.out.println("메뉴 수량 금액");
		for (MenuOrderResponse menuOrderResponse : menuOrderResponses.getMenuOrderResponses()) {
			System.out.println(menuOrderResponse.getMenuName() + " " + menuOrderResponse.getAmount() + " "
				+ menuOrderResponse.getPrice());
		}
		System.out.println();
		System.out.printf("## %d번 테이블의 결제를 진행합니다.\n", menuOrderResponses.getTableNumber());
	}

	public void printPaymentAmount(PayResponse payResponse) {
		System.out.println("## 최종 결제할 금액");
		System.out.printf("%d원\n", payResponse.getPaymentAmount());
	}
}
