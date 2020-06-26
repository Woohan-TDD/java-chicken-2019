package domain.table;

import static domain.table.Fixture.CHICKEN;
import static domain.table.Fixture.CHICKEN_ORDER;
import static domain.table.Fixture.CIDER_ORDER;
import static domain.table.Fixture.MAX_ORDER_AMOUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrdersTest {
    private Orders orders;

    @BeforeEach
    void setUp() {
        orders = new Orders();
    }

    @DisplayName("빈 orders 생성")
    @Test
    void constructor() {
        assertThat(new Orders()).isInstanceOf(Orders.class);
    }

    @DisplayName("새로운 order 추가")
    @Test
    void add_NewOrder() {
        orders.add(CHICKEN_ORDER);
        orders.add(CIDER_ORDER);

        assertThat(orders.getOrders()).hasSize(2);
    }

    @DisplayName("이미 존재하는 order 추가")
    @Test
    void add_AlreadyExistOrder() {
        orders.add(CHICKEN_ORDER);
        orders.add(CHICKEN_ORDER);

        assertThat(orders.getOrders()).hasSize(1);
    }

    @DisplayName("이미 존재하는 order를 개수 초과하여 추가 시도할 때 예외 발생")
    @Test
    void add_OverMaxAmount_ExceptionThrown() {
        Order maxAmountChickenOrder = new Order(CHICKEN, MAX_ORDER_AMOUNT);
        orders.add(maxAmountChickenOrder);

        assertThatThrownBy(() -> orders.add(CHICKEN_ORDER)).isInstanceOf(IllegalArgumentException.class);
    }
}
