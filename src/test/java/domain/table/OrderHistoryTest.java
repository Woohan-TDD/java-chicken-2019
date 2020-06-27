package domain.table;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.menu.Menu;
import domain.menu.MenuRepository;

public class OrderHistoryTest {
	private final MenuRepository menuRepository = new MenuRepository();

	private Menu menu;

	@BeforeEach
	void setUp() {
		menu = menuRepository.findById(1);
	}

	@DisplayName("주문내역이 잘 생성되는지 확인하는 테스트")
	@Test
	void createOrderHistoryTest() {
		OrderHistory orderHistory = new OrderHistory(menu, 1);
		assertAll(
			() -> assertThat(orderHistory.getMenu()).isEqualTo(menu),
			() -> assertThat(orderHistory.getQuantity()).isEqualTo(1)
		);
	}

	@DisplayName("한개의 주문내역의 결제 금액 계산 테스트")
	@Test
	void calculatePaymentAmountTest() {
		OrderHistory orderHistory = new OrderHistory(menu, 2);
		assertThat(orderHistory.calculatePaymentAmount()).isEqualTo(32000);
	}

	@DisplayName("toString() 테스트")
	@Test
	void toStringTest(){
		OrderHistory orderHistory = new OrderHistory(menu, 1);
		assertThat(orderHistory.toString()).isEqualTo("후라이드 1 16000");
	}

}
