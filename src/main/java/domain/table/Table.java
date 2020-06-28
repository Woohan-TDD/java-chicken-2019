package domain.table;

import domain.menu.Menu;
import domain.table.order.Orders;

import java.util.HashMap;
import java.util.Objects;

public class Table {
    private final int number;
    private final Orders orders;

    public Table(final int number) {
        this.number = number;
        this.orders = Orders.from(new HashMap<>());
    }

    public void resetOrders() {
        orders.reset();
    }

    public boolean isSameNumber(int number) {
        return this.number == number;
    }

    public void addOrder(final Menu menu, final int menuCount) {
        orders.add(menu, menuCount);
    }

    public boolean hasOrder() {
        return orders.hasOrder();
    }

    public int calculateTotalPrice() {
        return orders.calculateTotalPriceWithDiscount();
    }

    public Orders getOrders() {
        return orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return number == table.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}