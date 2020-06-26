package domain.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Orders {
    private final List<Order> orders = new ArrayList<>();

    public void add(final Order additionalOrder) {
        Optional<Order> maybeOrder = findOrderByMenu(additionalOrder);
        if (maybeOrder.isPresent()) {
            maybeOrder.ifPresent(order -> order.addAmount(additionalOrder.getOrderAmount()));
        } else {
            orders.add(additionalOrder);
        }
    }

    private Optional<Order> findOrderByMenu(final Order additionalOrder) {
        return orders.stream()
                .filter(order -> order.isSameMenu(additionalOrder))
                .findFirst();
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }
}
