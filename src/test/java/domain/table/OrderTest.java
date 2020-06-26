package domain.table;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.menu.Category;
import domain.menu.Menu;

class OrderTest {
    private static final Menu MENU = new Menu(1, "양념치킨", Category.CHICKEN, 16_000);
    private static final OrderAmount AMOUNT_ONE = new OrderAmount(1);

    @DisplayName("메뉴와 주문 개수를 입력받아 주문 생성")
    @Test
    void constructor() {
        assertThat(new Order(MENU, AMOUNT_ONE)).isInstanceOf(Order.class);
    }

    @DisplayName("생성자 호출 시 메뉴가 null인 경우 예외 발생")
    @Test
    void constructor_MenuIsNull_ExceptionThrown() {
        assertThatThrownBy(() -> new Order(null, AMOUNT_ONE))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("메뉴가 null입니다");
    }

    @DisplayName("생성자 호출 시 주문 수량이 null인 경우 예외 발생")
    @Test
    void constructor_OrderAmountIsNull_ExceptionThrown() {
        assertThatThrownBy(() -> new Order(MENU, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("주문 수량이 null입니다");
    }

    @DisplayName("기존에 존재하는 메뉴의 주문 추가")
    @Test
    void addOrder() {
        Order order = new Order(MENU, AMOUNT_ONE);
        OrderAmount addedOrderAmount = new OrderAmount(98);
        order.addAmount(addedOrderAmount);
        OrderAmount expect = new OrderAmount(99);

        assertThat(order.getOrderAmount()).isEqualTo(expect);
    }
}
