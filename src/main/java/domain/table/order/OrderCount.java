package domain.table.order;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OrderCount {
	private static final int MIN_RANGE = 1;
	private static final int MAX_RANGE = 99;

	private final int amount;

	private OrderCount(int amount) {
		validateRange(amount);
		this.amount = amount;
	}

	public static OrderCount of(int amount) {
		validateRange(amount);
		return OrderCountCache.CACHE.get(amount);
	}

	private static void validateRange(int amount) {
		if (amount < MIN_RANGE || amount > MAX_RANGE) {
			throw new IllegalArgumentException("잘못된 범위의 값을 입력했습니다. amount=" + amount);
		}
	}

	public OrderCount add(int weight) {
		return of(this.amount + weight);
	}

	public int getAmount() {
		return amount;
	}

	public long multiply(int price) {
		return amount * price;
	}

	static final class OrderCountCache {
		private static final Map<Integer, OrderCount> CACHE = IntStream.rangeClosed(MIN_RANGE, MAX_RANGE)
			.mapToObj(OrderCount::new)
			.collect(Collectors.toMap(OrderCount::getAmount, orderCount -> orderCount));
	}
}
