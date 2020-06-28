package domain.table;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TablesTest {

    private Tables tables;

    @BeforeEach
    void setUp() {
        tables = Tables.from(TableRepository.tables());
    }

    @Test
    void find() {
        Table expect = new Table(1);
        assertThat(tables.findTableByNumber(1)).isEqualTo(expect);
    }

    @Test
    void find_Exception() {
        assertThatThrownBy(() -> tables.findTableByNumber(11))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("없는 테이블 번호입니다.");
    }
}