package view;

import java.util.Scanner;

import domain.table.Table;

public class InputView {
	private final Scanner scanner;

	public InputView(Scanner scanner) {
		this.scanner = scanner;
	}

	public int inputSelectFunction() {
		System.out.println("## 원하는 기능을 선택하세요.");
		return scanner.nextInt();
	}

	public int inputTableNumber() {
		System.out.println("## 테이블을 선택하세요.");
		return scanner.nextInt();
	}

	public int inputSelectMenu() {
		System.out.println("## 등록할 메뉴를 선택하세요.");
		return scanner.nextInt();
	}

	public int inputMenuAmount() {
		System.out.println("## 메뉴의 수량을 입력하세요.");
		return scanner.nextInt();
	}

	public int inputSelectPayment(Table table) {
		System.out.printf("## %d번 테이블의 결제를 진행합니다.\n", table.getNumber());
		System.out.println("## 신용 카드는 1번, 현금은 2번");
		return scanner.nextInt();
	}
}
