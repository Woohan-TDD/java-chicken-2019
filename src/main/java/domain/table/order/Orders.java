package domain.table.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Orders {
    private final List<Order> orders = new ArrayList<>();

    public void add(final Order additionalOrder) {
        Optional<Order> maybeOrder = findOrderByMenu(additionalOrder);
        if (maybeOrder.isPresent()) {
            Order order = maybeOrder.get();
            order.addAmount(additionalOrder.getOrderAmount());
            return;
        }
        orders.add(additionalOrder);
    }

    public long calculateTotalPrice() {
        return orders.stream()
                .mapToLong(Order::calculateTotalPrice)
                .sum();
    }

    public int countChickens() {
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
