package controller;

import java.util.Arrays;
import java.util.function.Consumer;

public enum Command {
	ORDER(1, ChickenController::order),
	PAY(2, ChickenController::pay),
	EXIT(3, chickenController -> System.exit(0));

	private final int number;
	private final Consumer<ChickenController> consumer;

	Command(int number, Consumer<ChickenController> consumer) {
		this.number = number;
		this.consumer = consumer;
	}

	public static Command findByNumber(int number) {
		return Arrays.stream(values())
			.filter(command -> command.number == number)
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("값에 해당하는 명령이 없습니다. number : " + number));
	}

	public void execute(ChickenController chickenController) {
		consumer.accept(chickenController);
	}
}
