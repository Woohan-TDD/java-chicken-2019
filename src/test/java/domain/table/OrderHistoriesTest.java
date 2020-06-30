package domain.table;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.menu.Category;
import domain.menu.Menu;

class OrderHistoriesTest {

	private Menu chicken;
	private Menu beverage;

	@BeforeEach
	void setUp() {
		chicken = new Menu(1, "후라이드", Category.CHICKEN, 16_000);
		beverage = new Menu(1, "콜라", Category.BEVERAGE, 1_000);
	}

	@DisplayName("주문내역에 치킨이 몇개 있는지 확인하는 테스트")
	@Test
	void getChickenCountTest() {
		OrderHistories orderHistories = new OrderHistories(new ArrayList<>());
		orderHistories.add(new OrderHistory(chicken, 11));
		orderHistories.add(new OrderHistory(beverage, 10));
		assertThat(orderHistories.getChickenCount()).isEqualTo(11);
	}

	@DisplayName("주문내역이 삭제되는지 확인하는 테스트")
	@Test
	void clearOrderHistoriesTest() {
		OrderHistories orderHistories = new OrderHistories(new ArrayList<>());
		orderHistories.add(new OrderHistory(chicken, 11));
		orderHistories.add(new OrderHistory(beverage, 10));
		orderHistories.clear();
		assertThat(orderHistories.size()).isEqualTo(0);
	}

	@DisplayName("메뉴 중 주문 수량이 100개가 넘어가는 경우 예외처리")
	@Test
	void validateMenuQuantityGreaterThanNinetyNineTest() {
		OrderHistories orderHistories = new OrderHistories(new ArrayList<>());
		assertThatThrownBy(() -> orderHistories.add(new OrderHistory(chicken, 100)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("한 메뉴당 99까지만 주문할 수 있습니다. menu = [치킨] 1 - 후라이드 : 16000원");
	}

	@DisplayName("주문 한 후 주문수량이 100개가 넘는 경우 예외 처리")
	@Test
	void validateMenuQuantityGreaterThanNinetyNineTest2() {
		OrderHistories orderHistories = new OrderHistories(new ArrayList<>());
		orderHistories.add(new OrderHistory(chicken, 70));
		assertThatThrownBy(() -> orderHistories.add(new OrderHistory(chicken, 30)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("한 메뉴당 99까지만 주문할 수 있습니다. menu = [치킨] 1 - 후라이드 : 16000원");
	}
}