package domain.table;

import java.util.Objects;

import domain.table.order.Order;
import domain.table.order.Orders;

public class Table {
    private static final int MIN_NUMBER = 1;

    private final int number;
    private final Orders orders;

    private Table(final int number, final Orders orders) {
        validateNumber(number);
        Objects.requireNonNull(orders, "orders가 null입니다.");
        this.number = number;
        this.orders = orders;
    }

    public Table(final int number) {
        this(number, new Orders());
    }

    private void validateNumber(final int number) {
        if (number < MIN_NUMBER) {
            throw new IllegalArgumentException("테이블 번호는 양수만 입력 가능합니다.\n" +
                    "입력된 수: " + number);
        }
    }

    public boolean isSameNumber(final int number) {
        return this.number == number;
    }

    public void addOrder(final Order order) {
        orders.add(order);
    }

    public boolean hasOrder() {
        return orders.hasOrder();
    }

    public long calculateTotalPrice() {
        return orders.calculateTotalPrice();
    }

    public long countChickens() {
        return orders.countChickens();
    }

    public void clear() {
        orders.clear();
    }

    public int getNumber() {
        return number;
    }

    public Orders getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
