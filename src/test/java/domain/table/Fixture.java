package domain.table;

import domain.menu.Category;
import domain.menu.Menu;

public class Fixture {
    public static final Menu CHICKEN = new Menu(1, "양념치킨", Category.CHICKEN, 16_000);
    public static final Menu CIDER = new Menu(2, "사이다", Category.BEVERAGE, 2_000);

    public static final OrderAmount MIN_ORDER_AMOUNT = new OrderAmount(1);
    public static final OrderAmount MAX_ORDER_AMOUNT = new OrderAmount(99);

    public static final Order CHICKEN_ORDER = new Order(CHICKEN, MIN_ORDER_AMOUNT);
    public static final Order CIDER_ORDER = new Order(CIDER, MIN_ORDER_AMOUNT);
}
