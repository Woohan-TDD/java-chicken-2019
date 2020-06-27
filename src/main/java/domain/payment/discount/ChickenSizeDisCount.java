package domain.payment.discount;

import java.math.BigDecimal;

import domain.table.OrderHistories;

public class ChickenSizeDisCount implements DisCountStrategy{

	private static final int CHICKEN_SIZE_DISCOUNT_MONEY = 10000;
	private static final int CHICKEN_SIZE_DISCOUNT_AMOUNT = 10;

	@Override
	public BigDecimal calculateDiscount(final BigDecimal paymentAmount, final OrderHistories orderHistories) {
		return paymentAmount.subtract(disCountByChickenSize(orderHistories));
	}

	private BigDecimal disCountByChickenSize(OrderHistories orderHistories) {
		return new BigDecimal(
			CHICKEN_SIZE_DISCOUNT_MONEY * (orderHistories.getChickenCount() / CHICKEN_SIZE_DISCOUNT_AMOUNT));
	}
}
