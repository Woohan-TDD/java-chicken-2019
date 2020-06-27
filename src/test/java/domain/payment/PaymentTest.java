package domain.payment;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PaymentTest {

	@DisplayName("존재하는 결제 방식을 가져오는지 확인하는 테스트")
	@ParameterizedTest
	@CsvSource(value = {"1:CARD", "2:CASH"}, delimiter = ':')
	void ofTest(int number, Payment payment) {
		assertThat(Payment.of(number)).isEqualTo(payment);
	}

	@DisplayName("존재하지 않는 결제 방식을 가져오면 예외처리")
	@Test
	void notPaymentOfTest() {
		assertThatThrownBy(() -> Payment.of(3)).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("해당 결제 방식이 존재하지 않습니다. number = 3");
	}
}