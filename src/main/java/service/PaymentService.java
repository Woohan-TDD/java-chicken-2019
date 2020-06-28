package service;

import domain.Payment;
import domain.table.OrderAmountDiscount;
import domain.table.Table;
import domain.table.TableRepository;
import service.dto.PayRequest;
import service.dto.PayResponse;

public class PaymentService {
	private final TableRepository tableRepository;

	public PaymentService(TableRepository tableRepository) {
		this.tableRepository = tableRepository;
	}

	public PayResponse payOrder(PayRequest payRequest) {
		Table table = findTableByNumber(payRequest.getTableNumber());
		Payment payment = Payment.findByNumber(payRequest.getPaymentNumber());
		PayResponse payResponse = new PayResponse(
			payment.calculatePay(table.calculatePrice(OrderAmountDiscount.TEN_CHICKEN_DISCOUNT)));
		table.clearOrder();
		return payResponse;
	}

	private Table findTableByNumber(int number) {
		return tableRepository.findByNumber(number)
			.orElseThrow(() -> new IllegalArgumentException("해당하는 번호의 테이블이 없습니다. number : " + number));
	}
}
