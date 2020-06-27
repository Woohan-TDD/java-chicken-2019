package domain.payment;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.menu.Menu;
import domain.menu.MenuRepository;
import domain.payment.discount.ChickenSizeDisCount;
import domain.table.OrderHistories;
import domain.table.OrderHistory;

class CardPaymentTest {

	private final MenuRepository menuRepository = new MenuRepository();

	private CardPayment cardPayment;

	@BeforeEach
	void setUp() {
		cardPayment = new CardPayment(Collections.singletonList(new ChickenSizeDisCount()));
	}

	@DisplayName("치킨이 9마리 이하일때 할인 없이 금액이 나오는지 확인하는 테스트")
	@Test
	void payByNineLessChickenAmountTest() {
		Menu chicken1 = menuRepository.findById(1);
		Menu chicken2 = menuRepository.findById(2);
		Menu beverage = menuRepository.findById(21);
		OrderHistories orderHistories = new OrderHistories(
			Arrays.asList(
				new OrderHistory(chicken1, 2),
				new OrderHistory(chicken2, 2),
				new OrderHistory(beverage, 2))
		);

		Assertions.assertThat(cardPayment.pay(orderHistories).toString())
			.isEqualTo(BigInteger.valueOf(66000).toString());
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

		Assertions.assertThat(cardPayment.pay(orderHistories).toString())
			.isEqualTo(BigInteger.valueOf(184000).toString());
	}
}