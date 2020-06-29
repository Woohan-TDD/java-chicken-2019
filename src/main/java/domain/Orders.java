package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Orders {
    public static final int CHICKEN_SET_UNIT = 10;
    private final List<Order> orders = new ArrayList<>();

    public Orders() {
    }

    public void orderMenu(Order order) {
        if (isNotOrderedMenu(order)) {
            orders.add(order);
            return;
        }
        orders.stream()
                .filter(menu -> menu.isMenuSame(order))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 주문입니다."))
                .orderMore(order);
    }

    private boolean isNotOrderedMenu(Order order) {
        return orders.stream()
                .noneMatch(menu -> menu.isMenuSame(order));
    }

    public void resetOrder() {
        orders.clear();
    }

    public int calculateChickenSet() {
        int chickenCount = orders.stream()
                .filter(Order::isChicken)
                .mapToInt(Order::getCountNumber)
                .sum();
        return chickenCount / CHICKEN_SET_UNIT;
    }

    public boolean isOrderEmpty() {
        return orders.isEmpty();
    }

    //Todo 이름마음에안듬

    public int getOrderedMenuSize() {
        return orders.size();
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }
}
