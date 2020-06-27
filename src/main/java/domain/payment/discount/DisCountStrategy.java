package domain.payment.discount;

import java.math.BigDecimal;

import domain.table.OrderHistories;

public interface DisCountStrategy {
	BigDecimal calculateDiscount(BigDecimal paymentAmount, OrderHistories orderHistories);
}
