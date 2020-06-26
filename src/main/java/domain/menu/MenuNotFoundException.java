package domain.menu;

public class MenuNotFoundException extends RuntimeException {
    public MenuNotFoundException(final String message) {
        super(message);
    }
}
