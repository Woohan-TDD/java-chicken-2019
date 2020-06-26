package domain.table;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TableRepositoryTest {
    private TableRepository tableRepository;

    @BeforeEach
    void setUp() {
        tableRepository = new TableRepository();
    }

    @DisplayName("모든 테이블을 반환")
    @Test
    void findAll() {
        assertThat(tableRepository.findAll()).isNotNull();
    }

    @DisplayName("테이블 번호로 테이블을 찾음")
    @Test
    void findMenuByNumber() {
        assertThat(tableRepository.findTableByNumber(1)).isNotEmpty();
    }

    @DisplayName("테이블 번호가 일치하는 테이블이 존재하지 않는 경우 빈 옵셔널 반환")
    @Test
    void findMenuByNumber_FindNotExistNumber_ReturnEmptyOptional() {
        assertThat(tableRepository.findTableByNumber(50)).isEmpty();
    }
}
