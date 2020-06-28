package domain.table.order;

import domain.menu.Menu;

import java.util.*;

public class Orders {

    private static final int DISCOUNT_UNIT = 10000;
    private static final int COUNT_UNIT = 10;

    private final Map<Menu, Count> orders;

    private Orders(Map<Menu, Count> orders) {
        Objects.requireNonNull(orders, "Orders에서 Null 발생!");
        this.orders = new HashMap<>(orders);
    }

    public static Orders from(Map<Menu, Count> orders) {
        return new Orders(orders);
    }

    public void reset() {
        orders.clear();
    }

    public boolean hasOrder() {
        return !orders.isEmpty();
    }

    public void add(Menu menu, int menuCount) {
        if (orders.containsKey(menu)) {
            Count count = orders.get(menu);
            count.add(menuCount);
            return ;
        }
        orders.put(menu, Count.from(menuCount));
    }

    public int calculateTotalPriceWithDiscount() {
        return calculateTotalPrice() - calculateDiscount();
    }

    private int calculateTotalPrice() {
        return getMenus().stream()
                .mapToInt(Menu::getPrice)
                .sum();
    }

    private int calculateDiscount() {
        int chickenCount = getMenus().stream()
                .filter(Menu::isChicken)
                .mapToInt(menu -> getCount(menu).getCount())
                .sum();
        return (chickenCount / COUNT_UNIT) * DISCOUNT_UNIT;
    }

    public Set<Menu> getMenus() {
        return Collections.unmodifiableSet(orders.keySet());
    }

    public Count getCount(Menu menu) {
        return orders.get(menu);
    }

    public int getPrice(Menu menu) {
        Count count = getCount(menu);
        return menu.getPrice() * count.getCount();
    }
}