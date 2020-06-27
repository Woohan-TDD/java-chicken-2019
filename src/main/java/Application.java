import java.util.Scanner;

import controller.ChickenController;
import view.InputView;
import view.OutputView;

public class Application {
	public static void main(String[] args) {
		new ChickenController(new InputView(new Scanner(System.in)), new OutputView()).run();
	}
}
