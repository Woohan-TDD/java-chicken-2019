package service;

import java.util.List;

import domain.menu.Menu;
import domain.menu.MenuRepository;

public class MenuService {

	private final MenuRepository menuRepository;

	public MenuService(final MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	public List<Menu> findMenus() {
		return menuRepository.findAll();
	}
}
