package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuRepositoryTest {

    @Test
    @DisplayName("메 번호를 입력받고 해당하는 번호의 테이블 반환")
    void findMenuByNumber() {
        assertThat(MenuRepository.findMenuByNumber(3).isMenuNumber(3)).isTrue();
        assertThat(MenuRepository.findMenuByNumber(3).isMenuNumber(4)).isFalse();
    }

    @Test
    @DisplayName("찾으려는 메뉴 번호가 유효하지 않은 번호일 때 예외처리")
    void findMenuByNumberException() {
        assertThatThrownBy(() -> MenuRepository.findMenuByNumber(30))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 메뉴 번호가 존재하지 않습니다.");
    }


}