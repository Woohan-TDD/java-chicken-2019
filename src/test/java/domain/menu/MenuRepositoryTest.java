package domain.menu;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuRepositoryTest {
    private static final MenuRepository menuRepository = new MenuRepository();

    @DisplayName("모든 메뉴를 반환")
    @Test
    void findAll() {
        assertThat(menuRepository.findAll()).isNotNull();
    }

    @DisplayName("메뉴 번호로 메뉴를 찾음")
    @Test
    void findMenuByNumber() {
        assertThat(menuRepository.findMenuByNumber(1)).isNotEmpty();
    }

    @DisplayName("메뉴 번호가 일치하는 메뉴가 존재하지 않는 경우 빈 옵셔널 반환")
    @Test
    void findMenuByNumber_FindNotExistNumber_ReturnEmptyOptional() {
        assertThat(menuRepository.findMenuByNumber(50)).isEmpty();
    }
}
