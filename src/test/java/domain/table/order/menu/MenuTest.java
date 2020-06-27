package domain.table.order.menu;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.table.order.OrderCount;

class MenuTest {

	@Test
	void calculatePrice() {
		Menu menu = new Menu(1, "치킨", Category.CHICKEN, 5000);
		long actual = menu.calculatePrice(OrderCount.of(5));

		assertThat(actual).isEqualTo(25000);
	}

	@DisplayName("해당 메뉴가 치킨인지 확인한다.")
	@Test
	void isChicken() {
		Menu menu = new Menu(1, "치킨", Category.CHICKEN, 5000);
		assertThat(menu.isChicken()).isTrue();
	}
}