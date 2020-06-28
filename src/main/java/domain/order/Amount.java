package domain.order;

import java.util.HashMap;
import java.util.Map;

public class Amount {
	private static final int MAX = 99;
	private static final int MIN = 1;

	private final int amount;

	private Amount(int amount) {
		validate(amount);
		this.amount = amount;
	}

	private static void validate(int amount) {
		if (amount < MIN) {
			throw new IllegalArgumentException("메뉴 주문은 " + MIN + "개보다 적을 수 없습니다. amount : " + amount);
		}
		if (amount > MAX) {
			throw new IllegalArgumentException("메뉴 주문은 " + MAX + "개보다 많을 수 없습니다. amount : " + amount);
		}
	}

	public static Amount of(int amount) {
		validate(amount);
		return AmountCache.CACHE.get(amount);
	}

	public static Amount sum(Amount first, Amount second) {
		return Amount.of(first.amount + second.amount);
	}

	public Amount add(int other) {
		return Amount.of(amount + other);
	}

	public int calculateSum(int other) {
		return other + amount;
	}

	public int multiply(int price) {
		return amount * price;
	}

	public int getAmount() {
		return amount;
	}

	private static class AmountCache {
		private static Map<Integer, Amount> CACHE = new HashMap<>();

		static {
			for (int i = MIN; i <= MAX; i++) {
				CACHE.put(i, new Amount(i));
			}
		}

		private AmountCache() {
		}
	}
}
