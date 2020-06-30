package domain.payment;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import domain.payment.discount.DiscountStrategy;
import domain.table.OrderHistories;

public interface PaymentStrategy {
	BigDecimal pay(OrderHistories orderHistories);

	default Optional<DiscountStrategy> findDiscountStrategy(List<DiscountStrategy> discountStrategies,
		Class<? extends DiscountStrategy> discountStrategy) {
		return discountStrategies.stream()
			.filter(disCountStrategyValue -> disCountStrategyValue.getClass() == discountStrategy)
			.findFirst();
	}
}
