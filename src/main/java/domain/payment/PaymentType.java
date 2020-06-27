package domain.payment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domain.table.order.Order;

public enum PaymentType {
	CARD(1, Arrays.asList(new ChickenDiscountStrategy())),
	CACHE(2, Arrays.asList(new ChickenDiscountStrategy()), Arrays.asList(new CacheDiscountStrategy()));

	private final int type;
	private final List<DiscountStrategy> totalDiscountStrategies;
	private final List<DiscountStrategy> afterDiscountStrategies;

	PaymentType(int type, List<DiscountStrategy> totalDiscountStrategies) {
		this.type = type;
		this.totalDiscountStrategies = totalDiscountStrategies;
		this.afterDiscountStrategies = new ArrayList<>();
	}

	PaymentType(int type, List<DiscountStrategy> totalDiscountStrategies,
		List<DiscountStrategy> afterDiscountStrategies) {
		this.type = type;
		this.totalDiscountStrategies = totalDiscountStrategies;
		this.afterDiscountStrategies = afterDiscountStrategies;
	}

	public static PaymentType of(int type) {
		return Arrays.stream(values())
			.filter(paymentType -> paymentType.type == type)
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 결제방식입니다. type =" + type));
	}

	public long calculatePrice(Order order) {
		long money = order.calculateTotalMoney();
		money = preDiscount(order, money);
		return afterDiscount(order, money);
	}

	private long afterDiscount(Order order, long money) {
		for (DiscountStrategy discountStrategy : afterDiscountStrategies) {
			money = discountStrategy.calculate(money, order);
		}
		return money;
	}

	private long preDiscount(Order order, long money) {
		long weight = 0;

		for (DiscountStrategy discountStrategy : totalDiscountStrategies) {
			weight += discountStrategy.calculate(money, order);
		}

		money -= weight;
		return money;
	}
}
