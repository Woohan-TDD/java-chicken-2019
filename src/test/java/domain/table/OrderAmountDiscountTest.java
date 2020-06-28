package domain.table;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.menu.Category;
import domain.menu.Menu;
import domain.order.Amount;

class OrderAmountDiscountTest {
	@Test
	@DisplayName("치킨 10개당 10000원 할인이 제대로 되는지 확인")
	void calculate() {
		Table table = new Table(1);
		table.addMenu(new Menu(1, "치킨", Category.CHICKEN, 15_000), Amount.of(10));
		assertThat(table.calculatePrice(OrderAmountDiscount.TEN_CHICKEN_DISCOUNT)).isEqualTo(140_000);
	}
}