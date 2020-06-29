package controller;

import java.util.HashMap;
import java.util.Map;

import view.InputView;
import view.OutputView;

public class MainController {
    private static final int EXIT_SUCCESSFULLY = 0;
    private static final Map<Integer, Runnable> featureMap = new HashMap<>();

    private final OrderController orderController;
    private final InputView inputView;
    private final OutputView outputView;

    public MainController(final OrderController orderController, final InputView inputView,
            final OutputView outputView) {
        this.orderController = orderController;
        this.inputView = inputView;
        this.outputView = outputView;

        initRoutes(orderController);
    }

    private void initRoutes(final OrderController orderController) {
        featureMap.put(1, orderController::addOrderToTable);
        featureMap.put(2, orderController::paymentTable);
        featureMap.put(3, this::exitProgram);
    }

    private void exitProgram() {
        outputView.printExitMessage();
        System.exit(EXIT_SUCCESSFULLY);
    }

    public void run() {
        try {
            outputView.printFeatures();
            int featureNumber = inputView.inputFeatureNumber();
            route(featureNumber);
        } catch (NumberFormatException exception) {
            outputView.printWrongInputMessage();
        } catch (Exception exception) {
            outputView.printUnknownFeature(exception.getMessage());
        }
    }

    private void route(final int featureNumber) {
        Runnable feature = featureMap.getOrDefault(featureNumber, this::handleUnknownFeature);
        feature.run();
    }

    private void handleUnknownFeature() {
        throw new UnknownFeatureException("## 알 수 없는 명령이 호출되었습니다.");
    }
}
