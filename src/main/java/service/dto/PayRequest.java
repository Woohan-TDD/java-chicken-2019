package service.dto;

public class PayRequest {
	private int tableNumber;
	private int paymentNumber;

	public PayRequest(int tableNumber, int paymentNumber) {
		this.tableNumber = tableNumber;
		this.paymentNumber = paymentNumber;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public int getPaymentNumber() {
		return paymentNumber;
	}
}
