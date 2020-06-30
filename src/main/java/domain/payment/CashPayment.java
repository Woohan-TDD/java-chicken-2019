package domain.payment;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import domain.menu.Category;
import domain.payment.discount.CashDiscount;
import domain.payment.discount.ChickenSizeDiscount;
import domain.payment.discount.DiscountStrategy;
import domain.table.OrderHistories;
import domain.table.OrderHistory;

public class CashPayment implements PaymentStrategy {
	private final List<DiscountStrategy> disCountStrategies;

	public CashPayment(final List<DiscountStrategy> disCountStrategies) {
		this.disCountStrategies = disCountStrategies;
	}

	@Override
	public BigDecimal pay(final OrderHistories orderHistories) {
		BigDecimal paymentAmountOfChicken = calculatePaymentAmountOfChicken(orderHistories);
		BigDecimal paymentAmountOfTotal = paymentAmountOfChicken.add(calculatePaymentAmountOfBeverage(orderHistories));

		Optional<DiscountStrategy> disCount = findDiscountStrategy(disCountStrategies,CashDiscount.class);
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

		Optional<DiscountStrategy> disCount = findDiscountStrategy(disCountStrategies , ChickenSizeDiscount.class);
		if (disCount.isPresent()) {
			return disCount.get().calculateDiscount(new BigDecimal(chickenPaymentAmount), orderHistories);
		}

		return new BigDecimal(chickenPaymentAmount);
	}

	private BigDecimal calculatePaymentAmountOfBeverage(final OrderHistories orderHistories) {
		long beveragePaymentAmount = orderHistories.getOrderHistories()
			.stream()
			.filter(orderHistory -> orderHistory.isSameCategory(Category.BEVERAGE))
			.mapToLong(OrderHistory::calculatePaymentAmount)
			.sum();

		return new BigDecimal(beveragePaymentAmount);
	}
}
