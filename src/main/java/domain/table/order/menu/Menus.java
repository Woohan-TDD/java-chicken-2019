package domain.table.order.menu;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;

public class Menus {
	private final Map<Integer, Menu> menuById;

	public Menus(List<Menu> menus) {
		this.menuById = menus.stream()
			.collect(toMap(Menu::getNumber, menu -> menu));
	}

	public Menu findMenu(int menuNumber) {
		if (menuById.containsKey(menuNumber)) {
			return menuById.get(menuNumber);
		}
		throw new IllegalArgumentException("찾을 수 없는 메뉴입니다. menuNumber =" + menuNumber);
	}

	public Map<Integer, Menu> getMenuById() {
		return menuById;
	}
}
