package domain.payment.discount;

import java.math.BigDecimal;

import domain.table.OrderHistories;

public interface DiscountStrategy {
	BigDecimal calculateDiscount(BigDecimal paymentAmount, OrderHistories orderHistories);
}
