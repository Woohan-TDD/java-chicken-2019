package domain;

import java.util.Arrays;

public enum Payment {
	CARD(1, price -> price),
	CASH(2, price -> (int)(0.95 * price));

	private final int number;
	private final PricingDiscountStrategy pricingDiscountStrategy;

	Payment(int number, PricingDiscountStrategy pricingDiscountStrategy) {
		this.number = number;
		this.pricingDiscountStrategy = pricingDiscountStrategy;
	}

	public static Payment findByNumber(int number) {
		return Arrays.stream(values())
			.filter(payment -> payment.number == number)
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("해당하는 결제 수단이 존재하지 않습니다. number : " + number));
	}

	public int calculatePay(int price) {
		return pricingDiscountStrategy.calculate(price);
	}

	private interface PricingDiscountStrategy {
		int calculate(int price);
	}
}
