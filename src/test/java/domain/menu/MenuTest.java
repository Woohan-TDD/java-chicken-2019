package domain.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class MenuTest {

    private Menu menuChicken;
    private Menu menuBeverage;

    @BeforeEach
    void setUp() {
        menuChicken = new Menu(1, "후라이드", Category.CHICKEN, 16_000);
        menuBeverage = new Menu(21, "콜라", Category.BEVERAGE, 1_000);
    }

    @ParameterizedTest
    @CsvSource(value = {"1:true", "2:false"}, delimiter = ':')
    void isSameNumber(int number, boolean expect) {
        assertThat(menuChicken.isSameNumber(number)).isEqualTo(expect);
    }

    @Test
    void isChicken_True() {
        assertThat(menuChicken.isChicken()).isTrue();
    }

    @Test
    void isChicken_False() {
        assertThat(menuBeverage.isChicken()).isFalse();
    }

}