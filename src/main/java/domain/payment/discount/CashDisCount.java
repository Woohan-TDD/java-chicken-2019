package domain.payment.discount;

import java.math.BigDecimal;
import java.math.RoundingMode;

import domain.table.OrderHistories;

public class CashDisCount implements DisCountStrategy{

	private static final String CASH_DISCOUNT_PERCENT = "0.95";

	@Override
	public BigDecimal calculateDiscount(final BigDecimal paymentAmount, final OrderHistories orderHistories) {
		return paymentAmount.multiply(new BigDecimal(CASH_DISCOUNT_PERCENT))
			.setScale(0, RoundingMode.CEILING);
	}
}
