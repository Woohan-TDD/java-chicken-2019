package domain.table.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Orders {
    private static final int DISCOUNT_UNIT = 10;
    private static final int DISCOUNT_PRICE_PER_UNIT = 10000;

    private final List<Order> orders = new ArrayList<>();

    public void add(final Order additionalOrder) {
        Optional<Order> maybeOrder = findOrderByMenu(additionalOrder);
        if (maybeOrder.isPresent()) {
            maybeOrder.ifPresent(order -> order.addAmount(additionalOrder.getOrderAmount()));
        } else {
            orders.add(additionalOrder);
        }
    }

    public long calculateTotalPrice() {
        long totalPrice = sumAllPrice();
        return discountByChickenCount(totalPrice);
    }

    private long sumAllPrice() {
        return orders.stream()
                .mapToLong(Order::calculateTotalPrice)
                .sum();
    }

    private long discountByChickenCount(final long totalPrice) {
        int chickenCount = calculateTotalChickenCount();
        long discountPrice = chickenCount / DISCOUNT_UNIT * DISCOUNT_PRICE_PER_UNIT;
        return totalPrice - discountPrice;
    }

    private int calculateTotalChickenCount() {
        return orders.stream()
                .filter(Order::isChicken)
                .mapToInt(order -> order.getOrderAmount().getAmount())
                .sum();
    }

    private Optional<Order> findOrderByMenu(final Order additionalOrder) {
        return orders.stream()
                .filter(order -> order.isSameMenu(additionalOrder))
                .findFirst();
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    public boolean hasOrder() {
        return orders.size() > 0;
    }

    public void clear() {
        orders.clear();
    }
}
