package domain.order;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class AmountTest {
	@Test
	@DisplayName("생성 테스트")
	void of() {
		assertThat(Amount.of(1)).isNotNull();
	}

	@Test
	@DisplayName("같은 값이면 같은 객체인지 확인")
	void ofEqualObject() {
		assertThat(Amount.of(1)).isEqualTo(Amount.of(1));
	}

	@ParameterizedTest
	@DisplayName("생성 테스트 - 범위를 벗어날 시 예외 처리")
	@ValueSource(ints = {0, 100})
	void ofNegativeException(int amount) {
		assertThatThrownBy(() -> Amount.of(amount))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("메뉴 주문은");
	}

	@Test
	@DisplayName("주문 수량을 더하는 기능")
	void add() {
		assertThat(Amount.of(1).add(10)).isEqualTo(Amount.of(11));
	}

	@Test
	@DisplayName("주어진 값에 양을 곱하는 기능")
	void multiply() {
		assertThat(Amount.of(10).multiply(100)).isEqualTo(1_000);
	}

	@Test
	@DisplayName("서로 다른 Amount를 더하는 기능")
	void sum() {
		assertThat(Amount.sum(Amount.of(10), Amount.of(5))).isEqualTo(Amount.of(15));
	}

	@Test
	@DisplayName("서른 다른 Amount를 더했을 때 값을 나타내는 기능")
	void calculateSum() {
		assertThat(Amount.of(99).calculateSum(10)).isEqualTo(109);
	}
}
