package domain.table;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.menu.Menu;
import domain.menu.MenuRepository;

class TableTest {

	private Table table;

	@BeforeEach
	void setUp() {
		table = new Table(1, new ArrayList<>());

	}

	@DisplayName("해당 테이블 번호가 같은지 확인하는 테스트")
	@ParameterizedTest
	@CsvSource(value = {"1:true", "2:false"}, delimiter = ':')
	void isSameNumberTest(int tableNumber, boolean expected) {
		assertThat(table.isSameNumber(tableNumber)).isEqualTo(expected);
	}

	@DisplayName("테이블에 주문내역이 제대로 들어가는지 확인하는 테스트")
	@Test
	void addOrderHistoryTest() {
		MenuRepository menuRepository = new MenuRepository();
		Menu menu = menuRepository.findById(1);
		OrderHistory orderHistory = new OrderHistory(menu, 1);
		table.addOrderHistory(orderHistory);
		assertThat(table.getOrderHistories().getOrderHistories()).hasSize(1);
	}

	@DisplayName("toString() 테스트")
	@Test
	void toStringTest(){
		assertThat(table.toString()).isEqualTo("1");
	}
}