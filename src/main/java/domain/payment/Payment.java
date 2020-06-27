package domain.payment;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import domain.payment.discount.CashDisCount;
import domain.payment.discount.ChickenSizeDisCount;
import domain.table.OrderHistories;

public enum Payment {

	CARD(1, new CardPayment(Collections.singletonList(new ChickenSizeDisCount()))),
	CASH(2, new CardPayment(Arrays.asList(new ChickenSizeDisCount(), new CashDisCount())));

	private final int number;
	private final PaymentStrategy paymentStrategy;

	Payment(final int number, final PaymentStrategy paymentStrategy) {
		this.number = number;
		this.paymentStrategy = paymentStrategy;
	}

	public static Payment of(final int number){
		return Arrays.stream(Payment.values())
			.filter(payment -> payment.isSameNumber(number))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("해당 결제 방식이 존재하지 않습니다. number = " + number));
	}

	private boolean isSameNumber(final int number){
		return this.number == number;
	}

	public BigDecimal pay(final OrderHistories orderHistories) {
		return paymentStrategy.pay(orderHistories);
	}
}
