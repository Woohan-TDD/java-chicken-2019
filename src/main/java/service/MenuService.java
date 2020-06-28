package service;

import java.util.List;

import domain.menu.MenuRepository;
import service.dto.MenuResponse;

public class MenuService {
	private final MenuRepository menuRepository;

	public MenuService(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	public List<MenuResponse> findAllMenus() {
		return MenuResponse.listOf(menuRepository.findAll());
	}
}
