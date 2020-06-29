package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    @Test
    @DisplayName("메뉴이름이 같은지 확인")
    void isSameMenu() {
        Order order = new Order(MenuRepository.findMenuByNumber(1), new Count(1));
        Order sameOrderMenu = new Order(MenuRepository.findMenuByNumber(1), new Count(2));
        Order notSameOrderMenu = new Order(MenuRepository.findMenuByNumber(2), new Count(2));
        assertThat(order.isMenuSame(sameOrderMenu)).isTrue();
        assertThat(order.isMenuSame(notSameOrderMenu)).isFalse();
    }


    @Test
    @DisplayName("치킨인지 확인")
    void isChicken() {
        Order chicken = new Order(MenuRepository.findMenuByNumber(1), new Count(1));
        Order drink = new Order(MenuRepository.findMenuByNumber(21), new Count(1));
        assertThat(chicken.isChicken()).isTrue();
        assertThat(drink.isChicken()).isFalse();
    }

}