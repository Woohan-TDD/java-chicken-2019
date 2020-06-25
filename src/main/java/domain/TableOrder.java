package domain;

import java.util.HashMap;
import java.util.Map;

public class TableOrder {

    private final Map<Table, MenuOrder> tableOrders;

    private TableOrder(Map<Table, MenuOrder> tableOrders) {
        this.tableOrders = new HashMap<>(tableOrders);
    }

    public static TableOrder from(Map<Table, MenuOrder> tableOrders) {
        return new TableOrder(tableOrders);
    }
}
