package view;

import java.util.Scanner;

import utils.Converter;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);

	public static int inputFunction() {
		System.out.println("## 원하는 기능을 선택하세요,");
		return Converter.NumberConverterBy(scanner.nextLine());
	}

	public static int inputTableNumber() {
		System.out.println("## 테이블을 선택하세요.");
		return Converter.NumberConverterBy(scanner.nextLine());
	}

	public static int inputMenuNumber() {
		System.out.println("## 등록할 메뉴를 선택하세요.");
		return Converter.NumberConverterBy(scanner.nextLine());
	}

	public static int inputMenuQuantity() {
		System.out.println("## 메뉴의 수량을 입력하세요.");
		return Converter.NumberConverterBy(scanner.nextLine());
	}

	public static int inputPaymentNumber() {
		System.out.println("## 신용카드는 1번, 현금은 2번");
		return Converter.NumberConverterBy(scanner.nextLine());
	}
}
