package service;

import java.util.List;

import domain.menu.Menu;
import domain.menu.MenuNotFoundException;
import domain.menu.MenuRepository;

public class MenuService {
    private final MenuRepository menuRepository;

    public MenuService(final MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Menu> getMenus() {
        return menuRepository.findAll();
    }

    public Menu findMenuByNumber(final int menuNumber) {
        return menuRepository.findMenuByNumber(menuNumber)
                .orElseThrow(() -> new MenuNotFoundException("메뉴를 찾을 수 없습니다.\n" +
                        "입력 값: " + menuNumber));
    }
}
