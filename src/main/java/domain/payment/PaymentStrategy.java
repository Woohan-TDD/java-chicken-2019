package domain.payment;

import java.math.BigDecimal;

import domain.table.OrderHistories;

public interface PaymentStrategy {
	BigDecimal pay(OrderHistories orderHistories);
}
