package domain.payment;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.table.order.Order;
import domain.table.order.OrderCount;
import domain.table.order.menu.Category;
import domain.table.order.menu.Menu;

class PaymentTypeTest {

	@DisplayName("입력에 맞는 Payment타입을 확인한다.")
	@CsvSource(value = {"1,CARD", "2,CACHE"})
	@ParameterizedTest
	void of(int type, PaymentType actual) {
		PaymentType paymentType = PaymentType.of(type);

		assertThat(paymentType).isEqualTo(actual);
	}

	@DisplayName("잘못된 타입을 입력하면 에러가 발생한다.")
	@Test
	void name() {
		assertThatThrownBy(() -> PaymentType.of(-1))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("찾을 수 없는 결제방식입니다");
	}

	@DisplayName("카드계산으로 치킨의 할인을 계산한다.")
	@Test
	void calculatePrice() {
		PaymentType paymentType = PaymentType.CARD;
		Order order = new Order();
		order.add(new Menu(1, "치킨", Category.CHICKEN, 10000), OrderCount.of(10));

		long actual = paymentType.calculatePrice(order);

		assertThat(actual).isEqualTo(90000);
	}

	@DisplayName("현금 할인(5%)를 계산한다.")
	@Test
	void name3() {
		PaymentType paymentType = PaymentType.CACHE;
		Order order = new Order();
		order.add(new Menu(1, "얌얌", Category.BEVERAGE, 10000), OrderCount.of(5));
		order.add(new Menu(2, "얌얌이", Category.BEVERAGE, 10000), OrderCount.of(5));

		long actual = paymentType.calculatePrice(order);

		assertThat(actual).isEqualTo(95000);
	}

	@DisplayName("치킨할인과 현금할인을 계산한다.")
	@Test
	void name5() {
		PaymentType paymentType = PaymentType.CACHE;

		Order order = new Order();
		order.add(new Menu(1, "얌얌", Category.CHICKEN, 10000), OrderCount.of(5));
		order.add(new Menu(2, "얌얌이", Category.CHICKEN, 10000), OrderCount.of(5));

		long actual = paymentType.calculatePrice(order);

		assertThat(actual).isEqualTo(85_500);
	}
}