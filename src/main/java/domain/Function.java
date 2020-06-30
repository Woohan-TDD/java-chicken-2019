package domain;

import java.util.Arrays;

import controller.RunController;
import utils.Injection;

public enum Function {

	ORDER(1, Injection.orderController),
	PAYMENT(2, Injection.paymentController),
	EXIT(3, Injection.exitController);

	private final int number;
	private final RunController runController;

	Function(final int number, final RunController runController) {
		this.number = number;
		this.runController = runController;
	}

	public static Function of(final int number) {
		return Arrays.stream(Function.values())
			.filter(function -> function.isSameNumber(number)).
				findFirst()
			.orElseThrow(() -> new IllegalArgumentException("해당 기능을 찾을 수 없습니다. number = " + number));
	}

	private boolean isSameNumber(final int number) {
		return this.number == number;
	}

	public void getRunController() {
		runController.run();
	}
}
