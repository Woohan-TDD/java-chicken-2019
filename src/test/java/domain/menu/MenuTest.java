package domain.menu;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MenuTest {
	private Menu menu;

	@BeforeEach
	void setUp() {
		menu = new Menu(1, "후라이드", Category.CHICKEN, 16_000);
	}

	@DisplayName("같은 number인지 확인하는 테스트")
	@ParameterizedTest
	@CsvSource(value = {"1:true", "2:false"}, delimiter = ':')
	void isSameNumberTest(int number, boolean expected) {
		assertThat(menu.isSameNumber(number)).isEqualTo(expected);
	}

	@DisplayName("toString()을 구현한대로 출력하는지 확인하는 테스트")
	@Test
	void toStringTest() {
		assertThat(menu.toString()).isEqualTo("[치킨] 1 - 후라이드 : 16000원");
	}
}