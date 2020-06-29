package domain;

import java.util.Arrays;
import java.util.List;

public enum PaymentType {
    CARD(1) {
        @Override
        public double calculate(Orders orders) {
            List<Order> order = orders.getOrders();
            int totalPrice = order.stream()
                    .mapToInt(Order::getTotalPrice)
                    .sum();
            return totalPrice - calculateChickenDiscount(orders);
        }
    },

    CASH(2) {
        @Override
        public double calculate(Orders orders) {
            List<Order> order = orders.getOrders();
            double total = order.stream()
                    .mapToInt(Order::getTotalPrice)
                    .sum();
            return (total - calculateChickenDiscount(orders)) * CASH_DISCOUNT_RATE;
        }
    };

    public static final int CHICKEN_DISCOUNT_MONEY = 10000;
    public static final double CASH_DISCOUNT_RATE = 0.95;

    private final int payment;

    PaymentType(final int payment) {
        this.payment = payment;
    }

    public abstract double calculate(Orders orders);

    public static PaymentType of(int number) {
        return Arrays.stream(PaymentType.values())
                .filter(paymentType -> paymentType.payment == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 결제방식입니다."));
    }

    int calculateChickenDiscount(Orders orders) {
        return orders.calculateChickenSet() * CHICKEN_DISCOUNT_MONEY;
    }

}
