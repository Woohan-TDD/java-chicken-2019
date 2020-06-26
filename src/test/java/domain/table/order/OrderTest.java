package domain.table.order;

import static domain.table.Fixture.CHICKEN;
import static domain.table.Fixture.CHICKEN_ORDER;
import static domain.table.Fixture.CIDER;
import static domain.table.Fixture.CIDER_ORDER;
import static domain.table.Fixture.MAX_ORDER_AMOUNT;
import static domain.table.Fixture.ORDER_TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {
    private static final OrderAmount AMOUNT_ONE = new OrderAmount(1);

    @DisplayName("메뉴와 주문 개수를 입력받아 주문 생성")
    @Test
    void constructor() {
        assertThat(new Order(CHICKEN, AMOUNT_ONE)).isInstanceOf(Order.class);
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
        assertThatThrownBy(() -> new Order(CHICKEN, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("주문 수량이 null입니다");
    }

    @DisplayName("기존에 존재하는 메뉴의 주문 추가")
    @Test
    void addOrder() {
        Order order = new Order(CHICKEN, AMOUNT_ONE);
        OrderAmount addedOrderAmount = new OrderAmount(98);
        order.addAmount(addedOrderAmount);
        OrderAmount expect = new OrderAmount(99);

        assertThat(order.getOrderAmount()).isEqualTo(expect);
    }

    @DisplayName("같은 메뉴인지 여부를 반환")
    @Test
    void isSameMenu() {
        Order chickenOrder = new Order(CHICKEN, MAX_ORDER_AMOUNT);

        assertAll(
                () -> assertThat(CHICKEN_ORDER.isSameMenu(chickenOrder)).isTrue(),
                () -> assertThat(CHICKEN_ORDER.isSameMenu(CIDER_ORDER)).isFalse()
        );
    }

    @DisplayName("치킨인지 여부를 확인")
    @Test
    void isChicken() {
        assertAll(
                () -> assertThat(CHICKEN_ORDER.isChicken()).isTrue(),
                () -> assertThat(CIDER.isChicken()).isFalse()
        );
    }

    @DisplayName("주문 총액을 계산")
    @Test
    void calculateTotalPrice() {
        Order order = new Order(CHICKEN, ORDER_TEN);

        assertThat(order.calculateTotalPrice()).isEqualTo(160_000);
    }
}
