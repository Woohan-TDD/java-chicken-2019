package utils;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ConverterTest {

	@DisplayName("정상적으로 숫자로 변환하는지 테스트")
	@Test
	void NumberConverterTest() {
		assertThat(Converter.NumberConverterBy("1")).isEqualTo(1);
	}

	@DisplayName("숫자가 아닌 다른 숫자가 들어왔을때 예외처리")
	@ParameterizedTest
	@ValueSource(strings = {"", " ", "a", "가"})
	void NotNumberConverterTest(String value){
		assertThatThrownBy(() -> Converter.NumberConverterBy(value)).isInstanceOf(NumberFormatException.class)
			.hasMessage("숫자만 입력 가능합니다. value = " + value);
	}
}