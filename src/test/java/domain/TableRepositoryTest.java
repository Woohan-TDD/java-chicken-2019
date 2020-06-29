package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TableRepositoryTest {

    @Test
    @DisplayName("테이블 번호를 입력받고 해당하는 번호의 테이블 반환")
    void findTableByNumber() {
        assertThat(TableRepository.findTableByNumber(3).isTableNumber(3)).isTrue();
        assertThat(TableRepository.findTableByNumber(3).isTableNumber(4)).isFalse();
    }

    @Test
    @DisplayName("찾으려는 테이블 번호가 유효하지 않은 번호일 때 예외처리")
    void findTableByNumberException() {
        assertThatThrownBy(() -> TableRepository.findTableByNumber(20))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 테이블 번호가 존재하지 않습니다.");
    }

}