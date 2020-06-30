package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class FunctionTest {

	@DisplayName("원하는 기능을 찾는 테스트")
	@ParameterizedTest
	@CsvSource(value = {"1:ORDER", "2:PAYMENT", "3:EXIT"}, delimiter = ':')
	void ofTest(int number, Function function) {
		assertThat(Function.of(number)).isEqualTo(function);
	}

	@DisplayName("없는 기능을 요청할때 예외처리")
	@Test
	void notOfTest() {
		assertThatThrownBy(() -> Function.of(4)).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("해당 기능을 찾을 수 없습니다. number = 4");
	}
}
