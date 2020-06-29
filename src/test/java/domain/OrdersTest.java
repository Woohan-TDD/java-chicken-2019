package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrdersTest {

    private final List<Table> tables = TableRepository.tables();
    private Orders orders;

    @BeforeEach
    void setUp() {
        orders = new Orders();
    }

    @Test
    @DisplayName("주문이 비어있는지 확인")
    void isOrderEmpty() {
        assertThat(orders.isOrderEmpty()).isTrue();
    }

    @Test
    @DisplayName("주문 추가")
    void order() {
        Order order = new Order(MenuRepository.findMenuByNumber(1), new Count(3));
        orders.orderMenu(order);
        assertThat(orders.isOrderEmpty()).isFalse();
    }

    @Test
    @DisplayName("주문한 메뉴 종류의 수 확인")
    void size() {
        orders.orderMenu(new Order(MenuRepository.findMenuByNumber(1), new Count(3)));
        orders.orderMenu(new Order(MenuRepository.findMenuByNumber(2), new Count(3)));
        assertThat(orders.getOrderedMenuSize()).isEqualTo(2);
    }

    @Test
    @DisplayName("이미 주문한 메뉴 추가주문")
    void orderAgain() {
        Order order = new Order(MenuRepository.findMenuByNumber(1), new Count(3));
        orders.orderMenu(order);
        int firstOrderSize = orders.getOrderedMenuSize();
        orders.orderMenu(order);
        int secondOrderSize = orders.getOrderedMenuSize();
        assertThat(firstOrderSize).isEqualTo(secondOrderSize);
    }

    @Test
    @DisplayName("한 메뉴의 주문 수량이 100이상인 경우 예외처리")
    void orderFailOver100() {
        assertThatThrownBy(() -> orders.orderMenu(new Order(MenuRepository.findMenuByNumber(1), new Count(100))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("한 메뉴의 최대 주문량은 99입니다.");
    }

    @Test
    @DisplayName("주문초기화")
    void reset() {
        orders.orderMenu(new Order(MenuRepository.findMenuByNumber(1), new Count(1)));
        assertThat(orders.isOrderEmpty()).isFalse();
        orders.resetOrder();
        assertThat(orders.isOrderEmpty()).isTrue();
    }

    @Test
    @DisplayName("치킨이 10마리 단위로 몇 세트인지 반환")
    void chickenTenSet() {
        orders.orderMenu(new Order(MenuRepository.findMenuByNumber(2), new Count(23)));
        assertThat(orders.calculateChickenSet()).isEqualTo(2);
    }

}