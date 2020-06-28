package domain.menu;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.order.Amount;

class MenuTest {
	@Test
	@DisplayName("같은 number를 가지는 지 확인")
	void isSameNumber() {
		assertThat(new Menu(1, "맛치킨", Category.CHICKEN, 10_000).isSameNumber(1)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"CHICKEN, true", "BEVERAGE, false"})
	void isSameCategory(Category category, boolean expected) {
		assertThat(new Menu(1, "치킨일까", category, 10_000).isSameCategory(Category.CHICKEN)).isEqualTo(expected);
	}

	@Test
	@DisplayName("주문 수량에 맞게 가격을 계산하는 기능")
	void calculateMultiplePrice() {
		assertThat(new Menu(1, "치킨", Category.CHICKEN, 12_000).calculateMultiplePrice(Amount.of(10)))
			.isEqualTo(120_000);
	}
}