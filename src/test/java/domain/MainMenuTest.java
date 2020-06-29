package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MainMenuTest {

    @Test
    @DisplayName("기능 입력에 따른 메인타입 반환")
    void of() {
        assertThat(MainType.of(1)).isEqualTo(MainType.ORDER);
        assertThat(MainType.of(2)).isEqualTo(MainType.PAYMENT);
        assertThat(MainType.of(3)).isEqualTo(MainType.EXIT);
    }

    @Test
    @DisplayName("유효하지 않은 기능 입력에 따른 예외처리")
    void ofException() {
        assertThatThrownBy(() -> MainType.of(10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 메인메뉴입니다.");
    }

}