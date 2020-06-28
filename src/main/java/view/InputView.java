package view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int inputTableNumber() {
        System.out.println("## 주문할 테이블을 선택하세요.");
        return scanner.nextInt();
    }

    public int inputMenuNumber() {
        System.out.println("## 등록할 메뉴를 선택하세요.");
        return scanner.nextInt();
    }

    public int inputAmount() {
        System.out.println("## 메뉴의 수량을 입력하세요.");
        return scanner.nextInt();
    }

    public int inputPaymentNumber() {
        System.out.println("## 신용 카드는 1번, 현금은 2번");
        return scanner.nextInt();
    }

    public int inputCommandNumber() {
        System.out.println("## 메인 화면");
        System.out.println("1 - 주문 등록");
        System.out.println("2 - 결제하기");
        System.out.println("3 - 프로그램 종료");
        return scanner.nextInt();
    }
}
