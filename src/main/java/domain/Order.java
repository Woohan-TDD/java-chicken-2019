package domain;

import java.util.Objects;

public class Order {

    private final Menu menu;
    private Count count;

    public Order(Menu menu, Count count) {
        validate(menu, count);
        this.menu = menu;
        this.count = count;
    }

    private void validate(Menu menu, Count count) {
        Objects.requireNonNull(menu, "null 불가");
        Objects.requireNonNull(count, "null 불가");
    }

    public boolean isMenuSame(Order order) {
        return order.getMenu().equals(this.menu);
    }

    public void orderMore(Order order) {
        this.count = count.addCount(order.getCount());
    }

    public boolean isChicken() {
        return menu.isChicken();
    }

    public Menu getMenu() {
        return menu;
    }

    private Count getCount() {
        return count;
    }

    public int getCountNumber() {
        return count.getCount();
    }

    public int getTotalPrice() {
        return menu.getPrice() * count.getCount();
    }

}
