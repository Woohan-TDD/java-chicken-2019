package domain.table.payment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

public enum PaymentMethod implements DiscountStrategy {
    CREDIT_CARD(1, 1.00),
    CASH(2, 0.95);

    private final int paymentNumber;
    private final BigDecimal discountRate;

    PaymentMethod(final int paymentNumber, final double discountRate) {
        this.paymentNumber = paymentNumber;
        this.discountRate = new BigDecimal(discountRate);
    }

    public static PaymentMethod ofNumber(final int number) {
        return Stream.of(values())
                .filter(paymentMethod -> paymentMethod.isSameNumber(number))
                .findFirst()
                .orElseThrow(() -> new PaymentMethodNotFoundException("결제 방식이 존재하지 않습니다.\n" +
                        "입력 값: " + number));
    }

    private boolean isSameNumber(final int number) {
        return this.paymentNumber == number;
    }

    @Override
    public long discount(final long money) {
        BigDecimal discountedPrice = discountRate.multiply(new BigDecimal(money));
        discountedPrice = discountedPrice.setScale(1, RoundingMode.HALF_UP);
        return discountedPrice.longValue();
    }
}
