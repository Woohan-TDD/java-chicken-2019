package service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.menu.MenuRepository;
import domain.table.Table;
import domain.table.TableRepository;

class TableServiceTest {

	private final TableService tableService = new TableService(new TableRepository(), new MenuRepository());

	@DisplayName("전체 테이블 조회")
	@Test
	void findTablesTest() {
		assertThat(tableService.findTables()).hasSize(6);
	}

	@DisplayName("단일 테이블 조회")
	@Test
	void findByNumberTest() {
		assertThat(tableService.findByNumber(1)).isNotNull();
	}

	@DisplayName("숫자를 통해 테이블을 조회해 주문내역을 추가하는 기능 테스트")
	@Test
	void updateOrderHistoryByNumberTest() {
		tableService.updateOrderHistoryByNumber(1, 1, 1);
		Table table = tableService.findByNumber(1);
		assertThat(table.isUsingTable()).isTrue();
	}

	@DisplayName("테이블에서 결제내역이 삭제하는 기능 테스트")
	@Test
	void deleteOrderHistoriesByNumberTest(){
		tableService.updateOrderHistoryByNumber(1,1,1);
		tableService.deleteOrderHistoriesByNumber(1);
		Table table = tableService.findByNumber(1);
		assertThat(table.isUsingTable()).isFalse();
	}
}