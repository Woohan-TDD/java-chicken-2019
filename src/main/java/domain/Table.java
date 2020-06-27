package domain;

import java.util.Collections;
import java.util.List;

public class Table {
    private final int number;
    private final Orders orders;

    private Table(final int number, final Orders orders) {
        this.number = number;
        this.orders = orders;
    }

    public Table(final int number) {
        this(number, new Orders());
    }

    public boolean isTableNumber(int number) {
        return this.number == number;
    }

    public void order(Order order) {
        orders.orderMenu(order);
    }

    public boolean isOrderEmpty() {
        return orders.isOrderEmpty();
    }

    public int getOrderedMenuSize() {
        return orders.getOrderedMenuSize();
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders.getOrders());
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }

    public int getNumber() {
        return this.number;
    }
}
