import config.Injection;
import controller.MainController;

public class Application {
    private final MainController mainController;

    public Application(final MainController mainController) {
        this.mainController = mainController;
    }

    public static void main(String[] args) {
        Injection injection = new Injection();
        Application application = new Application(injection.provideMainController());
        application.run();
    }

    public void run() {
        while (true) {
            mainController.run();
        }
    }
}
