package domain.table;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.menu.Category;
import domain.menu.Menu;
import domain.menu.MenuRepository;
import domain.order.Amount;

class TableTest {
	@Test
	@DisplayName("테이블이 주어진 번호에 해당하는 지 테스트")
	void isSameNumber() {
		Assertions.assertThat(new Table(1).isSameNumber(1)).isTrue();
	}

	@Test
	@DisplayName("테이블의 주문이 비어있는 지 확인")
	void isOrderEmpty() {
		Table table = new Table(1);
		table.isOrderEmpty();
	}

	@Test
	@DisplayName("주문 정보를 테이블에 입력할 수 있는 지 확인")
	void addMenu() {
		Table table = new Table(1);
		table.addMenu(MenuRepository.menus().get(0), Amount.of(10));
		assertThat(table.isOrderEmpty()).isFalse();
	}

	@Test
	@DisplayName("주문 정보를 초기화하는 지 확인")
	void clearOrder() {
		Table table = new Table(1);
		table.addMenu(MenuRepository.menus().get(0), Amount.of(10));
		table.clearOrder();
		assertThat(table.isOrderEmpty()).isTrue();
	}

	@Test
	@DisplayName("테이블의 주문 내역을 계산")
	void calculateDiscountPrice() {
		Table table = new Table(1);
		table.addMenu(new Menu(1, "가", Category.CHICKEN, 10_000), Amount.of(10));
		assertThat(table.calculatePrice()).isEqualTo(100_000);
	}

	@Test
	@DisplayName("테이블의 주문 내역을 할인 정책에 따라 계산")
	void calculateDiscountPriceWithDiscount() {
		Table table = new Table(1);
		table.addMenu(new Menu(1, "가", Category.CHICKEN, 10_000), Amount.of(10));
		assertThat(table.calculatePrice(new TestOrderDiscountStrategy())).isEqualTo(50_000);
	}

	@Test
	@DisplayName("테이블의 주문 내역이 있는 경우 Order를 반환한다.")
	void getOrderIfExist() {
		Table table = new Table(1);
		table.addMenu(new Menu(1, "가", Category.CHICKEN, 10_000), Amount.of(10));
		assertThat(table.getOrderIfExist()).isNotNull();
	}

	@Test
	@DisplayName("테이블의 주문 내역이 없는 경우 예외를 발생시킨다.")
	void getOrderIfExistException() {
		Table table = new Table(1);
		assertThatThrownBy(table::getOrderIfExist)
			.isInstanceOf(IllegalStateException.class)
			.hasMessageContaining("비어");
	}
}