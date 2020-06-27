package domain;

import java.util.Arrays;

public enum PosMenu {
	ORDER(1),
	PAYMENT(2),
	EXIT(3);

	private final int function;

	PosMenu(int function) {
		this.function = function;
	}

	public static PosMenu of(int functionNumber) {
		return Arrays.stream(values())
			.filter(posMenu -> posMenu.function == functionNumber)
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 메뉴입니다. functionNumber=" + functionNumber));
	}
}
