package utils;

import controller.ExitController;
import controller.OrderController;
import controller.PaymentController;
import domain.menu.MenuRepository;
import domain.table.TableRepository;
import service.MenuService;
import service.TableService;

public class Injection {
	public static final MenuRepository menuRepository = new MenuRepository();
	public static final TableRepository tableRepository = new TableRepository();
	public static final MenuService menuService = new MenuService(Injection.menuRepository);
	public static final TableService tableService = new TableService(Injection.tableRepository, Injection.menuRepository);
	public static final OrderController orderController = new OrderController(Injection.menuService, Injection.tableService);
	public static final ExitController exitController = new ExitController();
	public static final PaymentController paymentController = new PaymentController(Injection.tableService);
}
