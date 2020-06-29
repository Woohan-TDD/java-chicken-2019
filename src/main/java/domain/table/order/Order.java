package domain.table.order;

import java.util.Objects;

import domain.menu.Menu;

public class Order {
    private final Menu menu;
    private OrderAmount orderAmount;

    public Order(final Menu menu, final OrderAmount orderAmount) {
        Objects.requireNonNull(menu, "메뉴가 null입니다.");
        Objects.requireNonNull(orderAmount, "주문 수량이 null입니다.");
        this.menu = menu;
        this.orderAmount = orderAmount;
    }

    public boolean isSameMenu(final Order that) {
        return this.menu.equals(that.menu);
    }

    public void addAmount(final OrderAmount orderAmount) {
        this.orderAmount = this.orderAmount.add(orderAmount);
    }

    public boolean isChicken() {
        return menu.isChicken();
    }

    public int calculateTotalPrice() {
        return orderAmount.multiply(menu.getPrice());
    }

    public Menu getMenu() {
        return menu;
    }

    public OrderAmount getOrderAmount() {
        return orderAmount;
    }

    @Override
    public String toString() {
        return menu.getName() + " " + orderAmount + " " + menu.getPrice();
    }
}
