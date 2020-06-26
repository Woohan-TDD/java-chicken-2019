package domain.table;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TableTest {
    @DisplayName("테이블 생성")
    @Test
    void constructor() {
        assertThat(new Table(1)).isInstanceOf(Table.class);
    }

    @DisplayName("테이블 번호가 양수가 아닌 경우 예외 발생")
    @Test
    void constructor_NumberIsNotPositive_ExceptionThrown() {
        assertThatThrownBy(() -> new Table(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("테이블 번호는 양수만 입력 가능합니다");
    }

    @DisplayName("입력받은 수와 테이블 번호가 같으면 true 반환")
    @Test
    void isSameNumber_NumberIsSame_ReturnTrue() {
        Table table = new Table(1);

        assertThat(table.isSameNumber(1)).isTrue();
    }

    @DisplayName("입력받은 수와 테이블 번호가 다르면 false 반환")
    @Test
    void isSameNumber_NumberIsNotSame_ReturnFalse() {
        Table table = new Table(1);

        assertThat(table.isSameNumber(2)).isFalse();
    }
}
