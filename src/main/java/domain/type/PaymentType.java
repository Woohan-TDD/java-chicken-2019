package domain.type;

import java.util.Arrays;
import java.util.function.Function;

public enum PaymentType {
    CARD(1, Long::valueOf),
    CASH(2, (price) -> Long.valueOf((price * 95) / 100));

    private final int payment;
    private final Function<Integer, Long> finalAmount;

    PaymentType(final int payment, final Function<Integer, Long> finalAmount) {
        this.payment = payment;
        this.finalAmount = finalAmount;
    }

    public static PaymentType find(final int payment) {
        return Arrays.stream(PaymentType.values())
                .filter(type -> type.payment == payment)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("원하는 결제 방식이 없습니다."));
    }

    public long payment(final int price) {
        return finalAmount.apply(price);
    }
}
