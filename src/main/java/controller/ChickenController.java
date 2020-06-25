package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class ChickenController {

    private static final ChickenController chickenController = new ChickenController();

    private ChickenController() {}

    public static ChickenController getInstance() {
        return chickenController;
    }

    public void execute() {
        try {
            work();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void work() {
        TableOrder tableOrder = TableOrder.from(TableMenuFactory.createTableOrder(
            TableRepository.tables(),
            MenuRepository.menus()
        ));
        FunctionType functionType;
        do {
            OutputView.printMainScreen();
            functionType = FunctionType.find(InputView.inputFunctionNumber());
        } while(functionType.isNotThree());
    }
}
