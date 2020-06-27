package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.table.order.OrderCount;

class OrderCountTest {
	@DisplayName("주문수량은 1~99를 넘지 않는다.")
	@ValueSource(ints = {0, 100})
	@ParameterizedTest
	void name(int badNumber) {
		assertThatThrownBy(() -> OrderCount.of(badNumber))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("잘못된 범위의 값을 입력했습니다.");
	}

	@DisplayName("주문수량에서 수량을 더한값을 확인한다.")
	@Test
	void add() {
		OrderCount orderCount = OrderCount.of(5);

		OrderCount actual = orderCount.add(5);

		assertThat(actual.getAmount()).isEqualTo(10);
	}
}