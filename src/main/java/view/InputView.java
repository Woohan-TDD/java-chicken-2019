package view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public int inputFeatureNumber() {
        System.out.println();
        System.out.println("## 원하는 기능을 선택하세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public int inputTableNumber() {
        System.out.println();
        System.out.println("## 테이블을 선택하세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public int inputMenuNumber() {
        System.out.println();
        System.out.println("## 등록할 메뉴를 선택하세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public int inputOrderAmount() {
        System.out.println();
        System.out.println("## 메뉴의 수량을 입하세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public int inputPaymentType() {
        System.out.println();
        System.out.println("## 1번 테이블의 결제를 진행합니다.");
        System.out.println("## 신용 카드는 1번, 현금은 2번");
        return Integer.parseInt(scanner.nextLine());
    }
}
