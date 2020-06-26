package domain.table;

public class OrderAmount {
    private static final int MIN_AMOUNT = 1;
    private static final int MAX_AMOUNT = 99;

    private final int amount;

    public OrderAmount(final int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(final int amount) {
        if (amount < MIN_AMOUNT || amount > MAX_AMOUNT) {
            throw new IllegalArgumentException("주문 개수의 범위를 벗어났습니다.\n" +
                    "입력 값: " + amount);
        }
    }

    public OrderAmount add(final OrderAmount addedOrderAmount) {
        return new OrderAmount(this.amount + addedOrderAmount.amount);
    }
}
