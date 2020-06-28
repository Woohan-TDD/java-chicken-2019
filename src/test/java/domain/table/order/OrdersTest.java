package domain.table.order;

import domain.menu.Menu;
import domain.menu.Category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class OrdersTest {

    private Orders orders;
    private Menu menu;

    @BeforeEach
    void setUp() {
        orders = Orders.from(new HashMap<>());
        menu = new Menu(2, "양념치킨", Category.CHICKEN, 16_000);
    }

    @Test
    void hasOrder_True() {
        orders.add(menu, 2);

        assertThat(orders.hasOrder()).isTrue();
    }

    @Test
    void hasOrder_False() {
        assertThat(orders.hasOrder()).isFalse();
    }

    @Test
    void addCount() {
        orders.add(menu, 2);
        orders.add(menu, 3);
        Count count = orders.getCount(menu);
        assertThat(count.getCount()).isEqualTo(5);
    }

    @ParameterizedTest
    @CsvSource(value = {"10:150000", "9:144000"}, delimiter = ':')
    void totalPrice(int count, int price) {
        orders.add(menu, count);
        assertThat(orders.calculateTotalPriceWithDiscount()).isEqualTo(price);
    }

    @Test
    void reset() {
        orders.add(menu, 2);
        orders.reset();

        assertThat(orders.hasOrder()).isFalse();
    }
}