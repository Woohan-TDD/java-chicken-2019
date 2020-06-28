package domain.table;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TableRepositoryTest {
	@Test
	@DisplayName("등록된 테이블을 number에 따라 찾을 수 있는 지 확인한다.")
	void findByNumber() {
		TableRepository tableRepository = new TableRepository();
		assertThat(tableRepository.findByNumber(1).get()).isEqualTo(TableRepository.tables().get(0));
	}
}