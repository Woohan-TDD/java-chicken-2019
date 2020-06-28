package domain.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Menus {

    private final List<Menu> menus;

    private Menus(final List<Menu> menus) {
        Objects.requireNonNull(menus,"Menus에서 Null 발생!");
        this.menus = new ArrayList<>(menus);
    }

    public static Menus from(final List<Menu> menus) {
        return new Menus(menus);
    }

    public Menu findMenuByNumber(final int menuNumber) {
        return menus.stream()
                .filter(menu -> menu.isSameNumber(menuNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("없는 메뉴입니다."));
    }

    public List<Menu> getMenus() {
        return Collections.unmodifiableList(menus);
    }
}