package domain;

import java.util.Arrays;

public enum MainType {

    ORDER(1),
    PAYMENT(2),
    EXIT(3);

    private final int number;

    MainType(int number) {
        this.number = number;
    }

    public static MainType of(int number) {
        return Arrays.stream(MainType.values())
                .filter(mainType -> mainType.number == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 메인메뉴입니다."));
    }
}
