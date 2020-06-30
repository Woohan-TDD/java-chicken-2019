package service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.menu.MenuRepository;

class MenuServiceTest {

	@DisplayName("전체 메뉴 조회")
	@Test
	void findTables() {
		MenuService menuRepository = new MenuService(new MenuRepository());
		assertThat(menuRepository.findMenus()).hasSize(8);
	}
}