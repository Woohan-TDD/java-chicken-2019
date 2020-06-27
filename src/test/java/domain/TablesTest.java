package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.table.Table;
import domain.table.TableRepository;
import domain.table.Tables;
import domain.table.order.OrderCount;
import domain.table.order.menu.Category;
import domain.table.order.menu.Menu;

class TablesTest {

	@DisplayName("테이블번호에 맞는 테이블을 가져온다.")
	@Test
	void getOrThrow() {
		Tables tables = new Tables(TableRepository.tables());

		Table table = tables.findTable(1);

		assertThat(table).isNotNull();
	}

	@DisplayName("등록되지 않는 테이블을 선택하면 에러를 발생한다.")
	@Test
	void name() {
		Tables tables = new Tables(TableRepository.tables());

		assertThatThrownBy(() -> tables.findTable(99))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("찾을 수 없는 테이블입니다.");
	}

	@DisplayName("주문이 있는 테이블을 선택한다.")
	@Test
	void getOrderedTable() {
		//given
		Tables tables = new Tables(TableRepository.tables());
		Table table = tables.findTable(1);

		Menu menu = new Menu(1, "치킨", Category.CHICKEN, 2_000);
		table.addOrder(menu, OrderCount.of(5));

		//when
		Table actual = tables.findOrderedTable(1);

		assertThat(actual).isNotNull();
	}

	@DisplayName("주문이 없는 테이블을 입력하면 에러가 발생한다.")
	@Test
	void name3() {
		Tables tables = new Tables(TableRepository.tables());

		assertThatThrownBy(() -> tables.findOrderedTable(3))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("주문되지 않는 테이블입니다.");
	}
}