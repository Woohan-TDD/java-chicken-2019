package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TableTest {

    private Table table;

    @BeforeEach
    void setUp() {
        table = TableRepository.findTableByNumber(1);
    }

    @Test
    @DisplayName("해당 번호의 테이블인지 확인")
    void isTableNumber() {
        Table table = new Table(3);
        assertThat(table.isTableNumber(3)).isTrue();
        assertThat(table.isTableNumber(4)).isFalse();
    }

    @Test
    @DisplayName("주문이 비었는지 확인")
    void checkOrderEmpty() {
        Table newTable = new Table(2);
        assertThat(newTable.isOrderEmpty()).isTrue();
    }

    @Test
    @DisplayName("주문내역 추가")
    void order() {
        Order order = new Order(MenuRepository.findMenuByNumber(1), new Count(3));
        table.order(order);
        assertThat(table.isOrderEmpty()).isFalse();
    }

    @Test
    @DisplayName("이미 주문한 메뉴를 더 추가 확인")
    void orderAgain() {
        Order order = new Order(MenuRepository.findMenuByNumber(1), new Count(3));
        table.order(order);
        int firstOrderSize = table.getOrderedMenuSize();
        table.order(order);
        int secondOrderSize = table.getOrderedMenuSize();
        assertThat(firstOrderSize).isEqualTo(secondOrderSize);
    }

    @ParameterizedTest
    @CsvSource(value = {"1:100", "2:99"}, delimiter = ':')
    @DisplayName("주문수량 100 이상이라 주문 실패 확인")
    void orderFailTest(int menu, int size) {
        table.order(new Order(MenuRepository.findMenuByNumber(2), new Count(1)));
        assertThatThrownBy(() -> table.order(new Order(MenuRepository.findMenuByNumber(menu), new Count(size))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("한 메뉴의 최대 주문량은 99입니다.");
    }

    @Test
    @DisplayName("주문 초기화")
    void resetOrder() {
        order();
        assertThat(table.isOrderEmpty()).isFalse();
        table.resetOrder();
        assertThat(table.isOrderEmpty()).isTrue();
    }
}