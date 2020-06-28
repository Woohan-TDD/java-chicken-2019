package domain.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MenusTest {

    private Menus menus;

    @BeforeEach
    void setUp() {
        menus = Menus.from(MenuRepository.menus());
    }

    @Test
    void find() {
        Menu expect = new Menu(1, "후라이드", Category.CHICKEN, 16_000);

        assertThat(menus.findMenuByNumber(1)).isEqualTo(expect);
    }

    @Test
    void find_Exception() {
        assertThatThrownBy(() -> menus.findMenuByNumber(7))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("없는 메뉴입니다.");
    }
}