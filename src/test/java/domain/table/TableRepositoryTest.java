package domain.table;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TableRepositoryTest {

	private final TableRepository tableRepository = new TableRepository();

	@DisplayName("해당 테이블을 찾아오는지 확인하는 테스트")
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 5, 6, 8})
	void findByNumberTest(int number) {
		assertThat(tableRepository.findByNumber(number).getNumber())
			.isEqualTo(number);
	}

	@DisplayName("없는 테이블을 조회할때 예외")
	@ParameterizedTest
	@ValueSource(ints = {4, 7, 9})
	void notFindByNumberTest(int number) {
		assertThatThrownBy(() -> tableRepository.findByNumber(number))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("해당 테이블을 찾을 수 없습니다. number = " + number);
	}
}