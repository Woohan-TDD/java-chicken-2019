package domain.payment;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.table.order.Order;
import domain.table.order.OrderCount;
import domain.table.order.menu.Category;
import domain.table.order.menu.Menu;

class ChickenDiscountStrategyTest {
	@DisplayName("치킨의 할인가를 계산한다.")
	@Test
	void calculate() {
		ChickenDiscountStrategy chickenDiscountStrategy = new ChickenDiscountStrategy();

		Order order = new Order();
		Menu 후라이드 = new Menu(1, "후라이드", Category.CHICKEN, 10_000);
		Menu 양념 = new Menu(2, "양념이드", Category.CHICKEN, 10_000);
		order.add(후라이드, OrderCount.of(5));
		order.add(양념, OrderCount.of(5));

		long actual = chickenDiscountStrategy.calculate(100_000, order);

		assertThat(actual).isEqualTo(10_000);
	}

}