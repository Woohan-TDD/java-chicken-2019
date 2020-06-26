package config;

import java.io.InputStream;
import java.util.Scanner;

import controller.MainController;
import controller.OrderController;
import domain.menu.MenuRepository;
import domain.table.TableRepository;
import service.MenuService;
import service.TableService;
import view.InputView;
import view.OutputView;

public class Injection {
    public MainController provideMainController() {
        Scanner scanner = provideScanner();
        InputView inputView = provideInputView(scanner);
        OutputView outputView = provideOutputView();

        TableRepository tableRepository = provideTableRepository();
        MenuRepository menuRepository = provideMenuRepository();

        MenuService menuService = provideMenuService(menuRepository);
        TableService tableService = provideTableService(tableRepository, menuService);

        OrderController orderController = provideTableController(tableService, menuService, inputView, outputView);
        return new MainController(orderController, inputView, outputView);
    }

    private OrderController provideTableController(final TableService tableService, final MenuService menuService,
            final InputView inputView, final OutputView outputView) {
        return new OrderController(tableService, menuService, inputView, outputView);
    }

    private TableService provideTableService(final TableRepository tableRepository,
            final MenuService menuService) {
        return new TableService(tableRepository, menuService);
    }

    private MenuService provideMenuService(final MenuRepository menuRepository) {
        return new MenuService(menuRepository);
    }

    private InputView provideInputView(final Scanner scanner) {
        return new InputView(scanner);
    }

    private OutputView provideOutputView() {
        return new OutputView();
    }

    private Scanner provideScanner() {
        return new Scanner(provideInputStream());
    }

    private InputStream provideInputStream() {
        return System.in;
    }

    private MenuRepository provideMenuRepository() {
        return new MenuRepository();
    }

    private TableRepository provideTableRepository() {
        return new TableRepository();
    }
}
