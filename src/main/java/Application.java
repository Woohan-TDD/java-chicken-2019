import controller.ChickenController;
import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        ChickenController chickenController = ChickenController.getInstance();
        chickenController.execute();
    }
}