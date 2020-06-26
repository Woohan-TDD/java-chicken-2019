package domain.table;

import static domain.table.Fixture.CHICKEN;
import static domain.table.Fixture.CHICKEN_ORDER;
import static domain.table.Fixture.ORDER_TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.table.order.Order;
import domain.table.order.Orders;
import domain.table.payment.PaymentMethod;

class TableTest {
    @DisplayName("테이블 생성")
    @Test
    void constructor() {
        assertThat(new Table(1)).isInstanceOf(Table.class);
    }

    @DisplayName("테이블 번호가 양수가 아닌 경우 예외 발생")
    @Test
    void constructor_NumberIsNotPositive_ExceptionThrown() {
        assertThatThrownBy(() -> new Table(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("테이블 번호는 양수만 입력 가능합니다");
    }

    @DisplayName("입력받은 수와 테이블 번호가 같으면 true 반환")
    @Test
    void isSameNumber_NumberIsSame_ReturnTrue() {
        Table table = new Table(1);

        assertThat(table.isSameNumber(1)).isTrue();
    }

    @DisplayName("입력받은 수와 테이블 번호가 다르면 false 반환")
    @Test
    void isSameNumber_NumberIsNotSame_ReturnFalse() {
        Table table = new Table(1);

        assertThat(table.isSameNumber(2)).isFalse();
    }

    @DisplayName("주문 추가")
    @Test
    void addOrder() {
        Table table = new Table(1);

        table.addOrder(CHICKEN_ORDER);

        Orders orders = table.getOrders();
        assertThat(orders.getOrders()).hasSize(1);
    }

    @DisplayName("주문이 없을 때 확인 메서드를 호출하면 false 반환")
    @Test
    void hasOrder_NotHasAnyOrder_ReturnFalse() {
        Table table = new Table(1);

        assertThat(table.hasOrder()).isFalse();
    }

    @DisplayName("주문이 있을 때 확인 메서드를 호출하면 false 반환")
    @Test
    void hasOrder_HasAnyOrder_ReturnTrue() {
        Table table = new Table(1);

        table.addOrder(CHICKEN_ORDER);

        assertThat(table.hasOrder()).isTrue();
    }

    @DisplayName("주문 총액을 계산")
    @Test
    void calculateTotalPrice() {
        Table table = new Table(1);
        Order order = new Order(CHICKEN, ORDER_TEN);

        table.addOrder(order);
        long totalPrice = table.calculateTotalPrice(PaymentMethod.CASH);

        assertThat(totalPrice).isEqualTo(142_500);
    }
}
