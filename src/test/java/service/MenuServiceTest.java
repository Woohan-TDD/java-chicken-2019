package service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.menu.MenuNotFoundException;
import domain.menu.MenuRepository;

class MenuServiceTest {
    private MenuService menuService;

    @BeforeEach
    void setUp() {
        MenuRepository menuRepository = new MenuRepository();
        menuService = new MenuService(menuRepository);
    }

    @DisplayName("모든 메뉴를 가져옴")
    @Test
    void getMenus() {
        assertThat(menuService.getMenus()).isNotNull();
    }

    @DisplayName("한 메뉴를 가져옴")
    @Test
    void findMenuByNumber() {
        assertThat(menuService.findMenuByNumber(1)).isNotNull();
    }

    @DisplayName("메뉴를 가져올 때 존재하지 않으면 예외 발생")
    @Test
    void findMenuByNumber_NotExistMenu_ExceptionThrown() {
        assertThatThrownBy(() -> menuService.findMenuByNumber(20))
                .isInstanceOf(MenuNotFoundException.class)
                .hasMessageContaining("메뉴를 찾을 수 없습니다");
    }
}