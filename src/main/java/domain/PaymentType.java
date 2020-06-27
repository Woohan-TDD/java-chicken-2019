package domain;

import java.util.Arrays;
import java.util.function.Function;

public enum PaymentType {
    CARD(1, Long::valueOf),
    CASH(2, (price) -> Long.valueOf((price * 95) / 100));

    private final int payment;
    private final Function<Integer, Long> finalAmount;

    PaymentType(int payment, Function<Integer, Long> finalAmount) {
        this.payment = payment;
        this.finalAmount = finalAmount;
    }

    public static PaymentType find(int payment) {
        return Arrays.stream(PaymentType.values())
                .filter(type -> type.payment == payment)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }

    public long payment(int price) {
        return finalAmount.apply(price);
    }
}
