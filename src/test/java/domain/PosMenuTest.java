package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class PosMenuTest {
	@DisplayName("숫자를 입력받아 그에 맞는 기능을 선택한다.")
	@CsvSource(value = {"1,ORDER", "2,PAYMENT", "3,EXIT"})
	@ParameterizedTest
	void name(int functionNumber, PosMenu posMenu) {
		PosMenu actual = PosMenu.of(functionNumber);
		assertThat(actual).isEqualTo(posMenu);
	}

	@DisplayName("잘못된 메뉴번호를 입력하면 에러가 발생한다.")
	@ValueSource(ints = {0, 4})
	@ParameterizedTest
	void name(int badNumber) {
		assertThatThrownBy(() -> PosMenu.of(badNumber))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("찾을 수 없는 메뉴입니다.");
	}
}