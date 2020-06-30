import domain.Function;
import view.InputView;
import view.OutputView;

public class Application {

	public static void main(String[] args) {
		while (true) {
			OutputView.printMain();
			Function function = Function.of(InputView.inputFunction());
			function.getRunController();
		}
	}
}
