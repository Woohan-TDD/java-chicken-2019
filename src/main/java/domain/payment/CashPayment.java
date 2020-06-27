package domain.payment;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import domain.menu.Category;
import domain.payment.discount.CashDisCount;
import domain.payment.discount.ChickenSizeDisCount;
import domain.payment.discount.DisCountStrategy;
import domain.table.OrderHistories;
import domain.table.OrderHistory;

public class CashPayment implements PaymentStrategy {
	private final List<DisCountStrategy> disCountStrategies;

	public CashPayment(final List<DisCountStrategy> disCountStrategies) {
		this.disCountStrategies = disCountStrategies;
	}

	@Override
	public BigDecimal pay(final OrderHistories orderHistories) {
		BigDecimal paymentAmountOfChicken = calculatePaymentAmountOfChicken(orderHistories);
		BigDecimal paymentAmountOfTotal = paymentAmountOfChicken.add(calculatePaymentAmountOfBeverage(orderHistories));

		Optional<DisCountStrategy> disCount = findDisCountStrategy(CashDisCount.class);
		if (disCount.isPresent()) {
			return disCount.get().calculateDiscount(paymentAmountOfTotal, orderHistories);
		}
		return paymentAmountOfTotal;
	}

	private BigDecimal calculatePaymentAmountOfChicken(final OrderHistories orderHistories) {
		long chickenPaymentAmount = orderHistories.getOrderHistories()
			.stream()
			.filter(orderHistory -> orderHistory.isSameCategory(Category.CHICKEN))
			.mapToLong(OrderHistory::calculatePaymentAmount)
			.sum();

		Optional<DisCountStrategy> disCount = findDisCountStrategy(ChickenSizeDisCount.class);
		if (disCount.isPresent()) {
			return disCount.get().calculateDiscount(new BigDecimal(chickenPaymentAmount), orderHistories);
		}

		return new BigDecimal(chickenPaymentAmount);
	}

	private BigDecimal calculatePaymentAmountOfBeverage(final OrderHistories orderHistories) {
		long beveragePaymentAmount = orderHistories.getOrderHistories()
			.stream()
			.filter(orderHistory -> orderHistory.isSameCategory(Category.BEVERAGE))
			.map(OrderHistory::calculatePaymentAmount)
			.mapToLong(n -> n)
			.sum();

		return new BigDecimal(beveragePaymentAmount);
	}

	private Optional<DisCountStrategy> findDisCountStrategy(Class<? extends DisCountStrategy> disCountStrategy) {
		return disCountStrategies.stream()
			.filter(disCountStrategyValue -> disCountStrategyValue.getClass() == disCountStrategy)
			.findFirst();
	}
}
