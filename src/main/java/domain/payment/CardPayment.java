package domain.payment;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import domain.menu.Category;
import domain.payment.discount.ChickenSizeDisCount;
import domain.payment.discount.DisCountStrategy;
import domain.table.OrderHistories;
import domain.table.OrderHistory;

public class CardPayment implements PaymentStrategy {

	private final List<DisCountStrategy> disCountStrategies;

	public CardPayment(final List<DisCountStrategy> disCountStrategies) {
		this.disCountStrategies = disCountStrategies;
	}

	@Override
	public BigDecimal pay(final OrderHistories orderHistories) {
		BigDecimal paymentAmountOfChicken = calculatePaymentAmountOfChicken(orderHistories);

		return paymentAmountOfChicken.add(
			calculatePaymentAmountOfBeverage(orderHistories)
		);
	}

	private BigDecimal calculatePaymentAmountOfChicken(final OrderHistories orderHistories) {
		long chickenPaymentAmount = orderHistories.getOrderHistories()
			.stream()
			.filter(orderHistory -> orderHistory.isSameCategory(Category.CHICKEN))
			.mapToLong(OrderHistory::calculatePaymentAmount)
			.sum();

		Optional<DisCountStrategy> disCount = findDisCountStrategy(ChickenSizeDisCount.class);

		if (disCount.isPresent()) {
			return disCount.get()
				.calculateDiscount(new BigDecimal(String.valueOf(chickenPaymentAmount)), orderHistories);
		}

		return new BigDecimal(String.valueOf(chickenPaymentAmount));
	}

	private Optional<DisCountStrategy> findDisCountStrategy(Class<? extends DisCountStrategy> disCountStrategy) {
		return disCountStrategies.stream()
			.filter(disCountStrategyValue -> disCountStrategyValue.getClass() == disCountStrategy)
			.findFirst();
	}

	private BigDecimal calculatePaymentAmountOfBeverage(final OrderHistories orderHistories) {
		long beveragePaymentAmount = orderHistories.getOrderHistories()
			.stream()
			.filter(orderHistory -> orderHistory.isSameCategory(Category.BEVERAGE))
			.map(OrderHistory::calculatePaymentAmount)
			.mapToLong(n -> n)
			.sum();

		return new BigDecimal(String.valueOf(beveragePaymentAmount));
	}
}
