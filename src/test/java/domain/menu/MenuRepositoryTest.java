package domain.menu;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuRepositoryTest {
	@Test
	@DisplayName("생성 테스트")
	void constructor() {
		Assertions.assertThat(new MenuRepository()).isNotNull();
	}

	@Test
	@DisplayName("등록된 메뉴를 number에 따라 찾을 수 있는 지 확인한다.")
	void findByNumber() {
		MenuRepository menuRepository = new MenuRepository();
		assertThat(menuRepository.findByNumber(1).get()).isEqualTo(MenuRepository.menus().get(0));
	}
}