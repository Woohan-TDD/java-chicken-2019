import java.util.Scanner;

import controller.ChickenController;
import controller.PageController;
import domain.menu.MenuRepository;
import domain.table.TableRepository;
import service.MenuService;
import service.PaymentService;
import service.TableOrderService;
import view.InputView;
import view.OutputView;

public class Application {
	public static void main(String[] args) {
		MenuRepository menuRepository = new MenuRepository();
		TableRepository tableRepository = new TableRepository();
		InputView inputView = new InputView(new Scanner(System.in));

		ChickenController chickenController = new ChickenController(inputView,
			new OutputView(), new MenuService(menuRepository),
			new PaymentService(tableRepository), new TableOrderService(tableRepository, menuRepository));

		PageController pageController = new PageController(inputView, chickenController);
		while (true) {
			pageController.run();
		}
	}
}
