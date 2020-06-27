package domain.payment;

import java.math.BigInteger;
import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.menu.Menu;
import domain.menu.MenuRepository;
import domain.table.OrderHistories;
import domain.table.OrderHistory;

class CashPaymentTest {

	MenuRepository menuRepository = new MenuRepository();

	@DisplayName("치킨이 9마리 이하일때 할인 없이 금액이 나오는지 확인하는 테스트")
	@Test
	void payTest() {
		Menu chicken1 = menuRepository.findById(1);
		Menu chicken2 = menuRepository.findById(2);
		Menu beverage = menuRepository.findById(21);
		CashPayment cashPayment = new CashPayment();
		OrderHistories orderHistories = new OrderHistories(
			Arrays.asList(
				new OrderHistory(chicken1, 2),
				new OrderHistory(chicken2, 2),
				new OrderHistory(beverage, 2))
		);

		Assertions.assertThat(cashPayment.pay(orderHistories).toString())
			.isEqualTo(BigInteger.valueOf(62700).toString());
	}
}