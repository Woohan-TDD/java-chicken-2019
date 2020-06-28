package service.dto;

public class PayResponse {
	private int paymentAmount;

	public PayResponse(int paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public int getPaymentAmount() {
		return paymentAmount;
	}
}
