package domain.payment;

import java.math.BigInteger;
import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.menu.Menu;
import domain.menu.MenuRepository;
import domain.payment.discount.CashDisCount;
import domain.payment.discount.ChickenSizeDisCount;
import domain.table.OrderHistories;
import domain.table.OrderHistory;

class CashPaymentTest {

	MenuRepository menuRepository = new MenuRepository();

	private CashPayment cashPayment;

	@BeforeEach
	void setUp() {
		cashPayment = new CashPayment(Arrays.asList(new ChickenSizeDisCount(), new CashDisCount()));
	}

	@DisplayName("치킨이 9마리 이하일때 할인 없이 금액이 나오는지 확인하는 테스트")
	@Test
	void payTest() {
		Menu chicken1 = menuRepository.findById(1);
		Menu chicken2 = menuRepository.findById(2);
		Menu beverage = menuRepository.findById(21);
		OrderHistories orderHistories = new OrderHistories(
			Arrays.asList(
				new OrderHistory(chicken1, 2),
				new OrderHistory(chicken2, 2),
				new OrderHistory(beverage, 2))
		);

		Assertions.assertThat(cashPayment.pay(orderHistories).toString())
			.isEqualTo(BigInteger.valueOf(62700).toString());
	}

	@DisplayName("치킨이 10마리 이상일때 할인 구현 금액이 나오는지 확인하는 테스트")
	@Test
	void payByTenGreaterChickenAmountTest() {
		Menu chicken1 = menuRepository.findById(1);
		Menu chicken2 = menuRepository.findById(2);
		Menu beverage = menuRepository.findById(21);
		OrderHistories orderHistories = new OrderHistories(
			Arrays.asList(
				new OrderHistory(chicken1, 5),
				new OrderHistory(chicken2, 7),
				new OrderHistory(beverage, 2))
		);

		Assertions.assertThat(cashPayment.pay(orderHistories).toString())
			.isEqualTo(BigInteger.valueOf(174800).toString());
	}
}