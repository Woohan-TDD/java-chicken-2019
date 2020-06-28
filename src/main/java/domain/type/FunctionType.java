package domain.type;

import java.util.Arrays;

public enum FunctionType {

    ORDER(1),
    PAYMENT(2),
    EXIT(3);

    private final int number;

    FunctionType(final int number) {
        this.number = number;
    }

    public static FunctionType find(final int inputNumber) {
        return Arrays.stream(FunctionType.values())
                .filter(type -> type.number == inputNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("1 ~ 3사이의 숫자만 가능합니다. 입력 값: " + inputNumber));
    }

    public boolean isNotThree() {
        return number != FunctionType.EXIT.number;
    }
}