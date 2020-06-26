package domain.table;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderAmountTest {
    @DisplayName("주문 개수를 생성")
    @ValueSource(ints = {1, 99})
    @ParameterizedTest
    void constructor(final int amount) {
        assertThat(new OrderAmount(amount)).isInstanceOf(OrderAmount.class);
    }

    @DisplayName("생성자 호출 시 주문 개수가 범위를 벗어난 경우 예외 발생")
    @ValueSource(ints = {0, 100})
    @ParameterizedTest
    void constructor_MenuIsNull_ExceptionThrown(final int amount) {
        assertThatThrownBy(() -> new OrderAmount(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("주문 개수의 범위를 벗어났습니다");
    }

    @DisplayName("기존 주문 수량에 추가")
    @ValueSource(ints = {1, 50, 98})
    @ParameterizedTest
    void add(final int addedAmount) {
        OrderAmount orderAmount = new OrderAmount(1);
        OrderAmount addedOrderAmount = new OrderAmount(addedAmount);

        assertThat(orderAmount.add(addedOrderAmount)).isNotNull();
    }

    @DisplayName("기존 주문 수량에 추가할 때 개수를 초과하면 예외 발생")
    @ValueSource(ints = {1, 99})
    @ParameterizedTest
    void add_OverMaxOrderCount_ExceptionThrown(final int addedAmount) {
        OrderAmount orderAmount = new OrderAmount(99);
        OrderAmount addedOrderAmount = new OrderAmount(addedAmount);

        assertThatThrownBy(() -> orderAmount.add(addedOrderAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("주문 개수의 범위를 벗어났습니다");
    }
}
