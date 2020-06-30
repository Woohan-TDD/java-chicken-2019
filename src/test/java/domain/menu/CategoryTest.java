package domain.menu;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CategoryTest {

	@DisplayName("제대로 출력되는지 확인하는 테스트")
	@ParameterizedTest
	@CsvSource(value = {"CHICKEN:[치킨]", "BEVERAGE:[음료]"}, delimiter = ':')
	void toStringTest(Category category, String expected) {
		assertThat(category.toString()).isEqualTo(expected);
	}
}