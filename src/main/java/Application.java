import domain.MenuRepository;

public class Application {
    public static void main(String[] args) {
        MenuRepository menuRepository = new MenuRepository();

        menuRepository.findAll();
    }
}
