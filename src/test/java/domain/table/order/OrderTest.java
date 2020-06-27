package domain.table.order;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.table.order.menu.Category;
import domain.table.order.menu.Menu;

class OrderTest {
	@DisplayName("메뉴를 추가하여 갯수를 확인한다.")
	@Test
	void name() {
		Order order = new Order();

		Menu 후라이드 = new Menu(1, "후라이드", Category.CHICKEN, 16_000);
		order.add(후라이드, OrderCount.of(5));

		Map<Menu, OrderCount> orderByCount = order.getOrderByCount();
		OrderCount actual = orderByCount.get(후라이드);

		assertThat(actual.getAmount()).isEqualTo(5);
	}

	@DisplayName("메뉴 추가시 한 메뉴가 (기존 수량 + 입력한 수량) 값이 99를 넘지 않는다.")
	@Test
	void name2() {
		Order order = new Order();

		Menu 후라이드 = new Menu(1, "후라이드", Category.CHICKEN, 16_000);
		order.add(후라이드, OrderCount.of(99));

		assertThatThrownBy(() -> order.add(후라이드, OrderCount.of(1)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("잘못된 범위의 값을 입력했습니다.");
	}

	@DisplayName("주문목록을 초기화한다.")
	@Test
	void clear() {
		Order order = new Order();
		Menu 후라이드 = new Menu(1, "후라이드", Category.CHICKEN, 16_000);
		order.add(후라이드, OrderCount.of(99));

		order.clear();

		Map<Menu, OrderCount> actual = order.getOrderByCount();
		assertThat(actual.size()).isEqualTo(0);
	}

	@DisplayName("주문이 비어 있는지 확인한다.")
	@Test
	void isEmpty() {
		Order order = new Order();
		assertThat(order.isEmpty()).isTrue();
	}

	@Test
	void calculateTotalMoney() {
		Order order = new Order();
		Menu 후라이드 = new Menu(1, "후라이드", Category.CHICKEN, 10_000);
		order.add(후라이드, OrderCount.of(5));

		long actual = order.calculateTotalMoney();

		assertThat(actual).isEqualTo(50000);
	}

	@DisplayName("주문에서 치킨의 수를 센다.")
	@Test
	void calculateChickenCount() {
		Order order = new Order();
		Menu 후라이드 = new Menu(1, "후라이드", Category.CHICKEN, 10_000);
		Menu 양념 = new Menu(2, "양념이드", Category.CHICKEN, 10_000);
		order.add(후라이드, OrderCount.of(5));
		order.add(양념, OrderCount.of(1));

		int actual = order.calculateChickenCount();
		assertThat(actual).isEqualTo(6);
	}
}