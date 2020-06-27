package domain.payment;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.table.order.Order;
import domain.table.order.OrderCount;
import domain.table.order.menu.Category;
import domain.table.order.menu.Menu;

class CacheDiscountStrategyTest {

	@DisplayName("현금 할인(5%)를 계산한다.")
	@Test
	void name3() {
		Order order = new Order();
		order.add(new Menu(1, "얌얌", Category.BEVERAGE, 10000), OrderCount.of(10));
		order.add(new Menu(2, "얌얌이", Category.BEVERAGE, 10000), OrderCount.of(10));
		CacheDiscountStrategy cacheDiscountStrategy = new CacheDiscountStrategy();

		long actual = cacheDiscountStrategy.calculate(100_000, order);

		assertThat(actual).isEqualTo(95000);
	}
}