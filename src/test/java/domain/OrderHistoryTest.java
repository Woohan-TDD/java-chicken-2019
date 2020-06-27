package domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.menu.Menu;
import domain.menu.MenuRepository;
import domain.table.OrderHistory;

public class OrderHistoryTest {

	@DisplayName("주문내역이 잘 생성되는지 확인하는 테스트")
	@Test
	void createOrderHistory() {
		MenuRepository menuRepository = new MenuRepository();
		Menu menu = menuRepository.findById(1);
		OrderHistory orderHistory = new OrderHistory(menu, 1);
		assertAll(
			() -> assertThat(orderHistory.getMenu()).isEqualTo(menu),
			() -> assertThat(orderHistory.getQuantity()).isEqualTo(1)
		);
	}

	@DisplayName("한개의 주문내역의 결제 금액 계산 테스트")
	@Test
	public void calculatePaymentAmount() {
		MenuRepository menuRepository = new MenuRepository();
		Menu menu = menuRepository.findById(1);
		OrderHistory orderHistory = new OrderHistory(menu, 2);
		assertThat(orderHistory.calculatePaymentAmount()).isEqualTo(32000);
	}
}
