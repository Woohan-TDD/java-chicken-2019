package domain.payment;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import domain.menu.Category;
import domain.payment.discount.ChickenSizeDiscount;
import domain.payment.discount.DiscountStrategy;
import domain.table.OrderHistories;
import domain.table.OrderHistory;

public class CardPayment implements PaymentStrategy {

	private final List<DiscountStrategy> disCountStrategies;

	public CardPayment(final List<DiscountStrategy> disCountStrategies) {
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

		Optional<DiscountStrategy> disCount = findDiscountStrategy(disCountStrategies , ChickenSizeDiscount.class);

		if (disCount.isPresent()) {
			return disCount.get()
				.calculateDiscount(new BigDecimal(String.valueOf(chickenPaymentAmount)), orderHistories);
		}

		return new BigDecimal(String.valueOf(chickenPaymentAmount));
	}

	private BigDecimal calculatePaymentAmountOfBeverage(final OrderHistories orderHistories) {
		long beveragePaymentAmount = orderHistories.getOrderHistories()
			.stream()
			.filter(orderHistory -> orderHistory.isSameCategory(Category.BEVERAGE))
			.mapToLong(OrderHistory::calculatePaymentAmount)
			.sum();

		return new BigDecimal(String.valueOf(beveragePaymentAmount));
	}
}
