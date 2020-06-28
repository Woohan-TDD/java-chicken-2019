package domain.table;

import domain.menu.Menu;
import domain.menu.Category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class TableTest {

    private Table table;
    private Menu menu;

    @BeforeEach
    void setUp() {
        table = new Table(1);
        menu = new Menu(1, "후라이드", Category.CHICKEN, 16_000);
    }

    @ParameterizedTest
    @CsvSource(value = {"1:true", "2:false"}, delimiter = ':')
    void isSameNumber(int number, boolean expect) {
        assertThat(table.isSameNumber(number)).isEqualTo(expect);
    }

    @Test
    void addOrder() {
        table.addOrder(menu, 1);

        assertThat(table.hasOrder()).isTrue();
    }

    @Test
    void reset() {
        table.addOrder(menu, 1);
        table.resetOrders();

        assertThat(table.hasOrder()).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"10:150000", "9:144000"}, delimiter = ':')
    void calculate(int count, int price) {
        table.addOrder(menu, count);
        assertThat(table.calculateTotalPrice()).isEqualTo(price);
    }
}