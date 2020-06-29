package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MenuTest {

    @Test
    @DisplayName("해당 번호의 메뉴인지 확인")
    void isMenuNumber() {
        Menu menu = new Menu(1, "치킨", Category.CHICKEN, 10000);
        assertThat(menu.isMenuNumber(1)).isTrue();
        assertThat(menu.isMenuNumber(2)).isFalse();

    }

    @Test
    @DisplayName("치킨인지 확인")
    void isChicken() {
        Menu chicken = new Menu(1, "치킨", Category.CHICKEN, 10000);
        Menu drink = new Menu(1, "음료", Category.BEVERAGE, 10000);
        assertThat(chicken.isChicken()).isTrue();
        assertThat(drink.isChicken()).isFalse();
    }

}