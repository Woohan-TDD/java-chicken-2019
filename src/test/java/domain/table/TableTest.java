package domain.table;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.table.order.Order;
import domain.table.order.OrderCount;
import domain.table.order.menu.Category;
import domain.table.order.menu.Menu;

class TableTest {

	@DisplayName("테이블에 주문을 추가한다.")
	@Test
	void addOrder() {
		Table table = new Table(1, new Order());
		Menu 치킨 = new Menu(1, "치킨", Category.CHICKEN, 2_000);

		table.addOrder(치킨, OrderCount.of(5));

		Map<Menu, OrderCount> actual = table.getOrder().getOrderByCount();
		assertThat(actual.get(치킨)).isEqualTo(OrderCount.of(5));
	}

	@DisplayName("테이블의 Order를 비운다.")
	@Test
	void clear() {
		//given
		Table table = new Table(1, new Order());
		Menu 치킨 = new Menu(1, "치킨", Category.CHICKEN, 2_000);
		table.addOrder(치킨, OrderCount.of(5));

		//when
		table.clear();

		//then
		Map<Menu, OrderCount> actual = table.getOrder().getOrderByCount();
		assertThat(actual.size()).isEqualTo(0);
	}

	@DisplayName("주문이 비어있는지 확인한다.")
	@Test
	void isEmpty() {
		Table table = new Table(1, new Order());

		assertThat(table.isEmpty()).isTrue();
	}

}