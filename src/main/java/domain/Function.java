package domain;

import java.util.Arrays;

import controller.ExitController;
import controller.OrderController;
import controller.PaymentController;
import controller.RunController;
import domain.menu.MenuRepository;
import domain.table.TableRepository;
import service.MenuService;
import service.TableService;

public enum Function {

	ORDER(1, new OrderController(new MenuService(new MenuRepository()),
		new TableService(new TableRepository(), new MenuRepository()))),
	PAYMENT(2, new PaymentController(new TableService(new TableRepository(), new MenuRepository()))),
	EXIT(3, new ExitController());

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
