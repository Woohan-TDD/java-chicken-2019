package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CountTest {

    @Test
    @DisplayName("정상적 객체 생성 확인")
    void createCount() {
        Count count = new Count(3);
        assertThat(count).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    @DisplayName("0 이하의 음수 예외처리")
    void validate(int input) {
        assertThatThrownBy(() -> new Count(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("0이하로 주문할 수 없습니다.");
    }

    @Test
    @DisplayName("100이상 주문 예외처리")
    void validateOver100() {
        assertThatThrownBy(() -> new Count(100))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("한 메뉴의 최대 주문량은 99입니다.");
    }

    @Test
    @DisplayName("주문량 추가")
    void addCount() {
        Count count = new Count(3);
        count = count.addCount(new Count(4));
        assertThat(count.getCount()).isEqualTo(7);
    }
}