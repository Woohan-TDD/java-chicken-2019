package service;

import java.util.Arrays;
import java.util.List;

import domain.menu.Menu;
import domain.table.Table;
import domain.table.TableNotFoundException;
import domain.table.TableRepository;
import domain.table.order.Order;
import domain.table.order.OrderAmount;
import domain.table.order.OrderNotFoundException;
import domain.table.payment.ChickenDiscountStrategy;
import domain.table.payment.DiscountStrategies;
import domain.table.payment.PaymentMethod;

public class TableService {
    private final TableRepository tableRepository;
    private final MenuService menuService;

    public TableService(final TableRepository tableRepository, final MenuService menuService) {
        this.tableRepository = tableRepository;
        this.menuService = menuService;
    }

    public List<Table> getTables() {
        return tableRepository.findAll();
    }

    public void addOrder(final int tableNumber, final int menuNumber, final int amount) {
        Table table = getTableByNumber(tableNumber);
        Menu menu = menuService.getMenuByNumber(menuNumber);

        OrderAmount orderAmount = new OrderAmount(amount);
        Order order = new Order(menu, orderAmount);

        table.addOrder(order);
    }

    public long payment(final int tableNumber, final int paymentNumber) {
        Table table = getTableByNumber(tableNumber);
        validateOrders(table);

        long totalPrice = table.calculateTotalPrice();
        long chickenCount = table.countChickens();

        DiscountStrategies discountStrategies = new DiscountStrategies(
                Arrays.asList(new ChickenDiscountStrategy(chickenCount), PaymentMethod.ofNumber(paymentNumber)));
        long discountedMoney = discountStrategies.discount(totalPrice);
        table.clear();
        return discountedMoney;
    }

    public Table getTableByNumber(final int tableNumber) {
        return tableRepository.findTableByNumber(tableNumber)
                .orElseThrow(() -> new TableNotFoundException("테이블을 찾을 수 없습니다.\n" +
                        "입력 값: " + tableNumber));
    }

    public Table getOrderExistTableByNumber(final int tableNumber) {
        Table table = getTableByNumber(tableNumber);
        validateOrders(table);
        return table;
    }

    private void validateOrders(final Table table) {
        if (!table.hasOrder()) {
            throw new OrderNotFoundException("주문 내역이 존재하지 않습니다.\n");
        }
    }
}
