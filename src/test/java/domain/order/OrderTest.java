package domain.order;

import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.menu.Category;
import domain.menu.Menu;

public class OrderTest {
	private Map<Menu, Amount> menuOrders = new HashMap<>();

	@BeforeEach
	void setUp() {
		menuOrders.put(new Menu(1, "가", Category.CHICKEN, 10_000), Amount.of(10));
		menuOrders.put(new Menu(2, "나", Category.CHICKEN, 15_000), Amount.of(5));
	}

	@Test
	@DisplayName("생성 테스트")
	void constructor() {
		assertThat(new Order(menuOrders)).isNotNull();
	}

	@Test
	@DisplayName("빈 주문 내역을 반환")
	void empty() {
		assertThat(Order.empty().isEmpty()).isTrue();
	}

	@Test
	@DisplayName("주문 내역의 총 가격을 계산")
	void calculateOrderPrice() {
		Order order = new Order(menuOrders);
		assertThat(order.calculatePrice()).isEqualTo(175_000);
	}

	@Test
	@DisplayName("새로운 주문 내역을 추가")
	void add() {
		Order order = new Order(menuOrders);
		order.addMenu(new Menu(3, "다", Category.BEVERAGE, 2_000), Amount.of(2));
		assertThat(order.getMenuOrders()).hasSize(3);
	}

	@Test
	@DisplayName("주문 내역이 비어있는 지 확인")
	void isEmpty() {
		assertThat(new Order(Collections.emptyMap()).isEmpty()).isTrue();
	}

	@Test
	@DisplayName("주문 내역 초기화")
	void clear() {
		Order order = new Order(menuOrders);
		order.clear();
		assertThat(order.isEmpty()).isTrue();
	}
}
