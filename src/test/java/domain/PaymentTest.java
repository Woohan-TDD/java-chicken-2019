package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaymentTest {
	@Test
	@DisplayName("결제 수단에 따른 할인액을 계산")
	void calculatePay() {
		assertThat(Payment.CASH.calculatePay(10_000)).isEqualTo(9_500);
	}

	@Test
	@DisplayName("숫자에 해당하는 결제 방식을 불러오는 지 확인")
	void findByNumber() {
		assertThat(Payment.findByNumber(1)).isEqualTo(Payment.CARD);
	}
}