package service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.menu.MenuRepository;
import domain.table.Table;
import domain.table.TableNotFoundException;
import domain.table.TableRepository;
import domain.table.order.OrderNotFoundException;

class TableServiceTest {
    private TableService tableService;

    @BeforeEach
    void setUp() {
        TableRepository tableRepository = new TableRepository();
        MenuRepository menuRepository = new MenuRepository();
        MenuService menuService = new MenuService(menuRepository);
        tableService = new TableService(tableRepository, menuService);
    }

    @DisplayName("모든 테이블을 가져옴")
    @Test
    void getTables() {
        assertThat(tableService.getTables()).isNotNull();
    }

    @DisplayName("테이블 번호로 테이블 하나를 찾음")
    @Test
    void findTableByNumber() {
        assertThat(tableService.getTableByNumber(1)).isNotNull();
    }

    @DisplayName("테이블 번호가 일치하는 테이블이 존재하지 않으면 예외 발생")
    @Test
    void findTableByNumber_NotExistTable_ExceptionThrown() {
        assertThatThrownBy(() -> tableService.getTableByNumber(20))
                .isInstanceOf(TableNotFoundException.class)
                .hasMessageContaining("테이블을 찾을 수 없습니다");
    }

    @DisplayName("특정 테이블에 주문을 추가")
    @Test
    void addOrder() {
        int tableNumber = 1;
        int menuNumber = 1;
        int amount = 10;

        tableService.addOrder(tableNumber, menuNumber, amount);

        Table table = tableService.getTableByNumber(tableNumber);
        assertThat(table.hasOrder()).isTrue();
    }

    @DisplayName("특정 테이블의 금액을 지불")
    @CsvSource(value = {"1, 150000", "2, 142500"})
    @ParameterizedTest
    void payment(final int paymentNumber, final long expect) {
        int tableNumber = 1;
        int menuNumber = 1;
        int amount = 10;

        tableService.addOrder(tableNumber, menuNumber, amount);
        long payment = tableService.payment(tableNumber, paymentNumber);
        Table table = tableService.getTableByNumber(tableNumber);

        assertAll(
                () -> assertThat(payment).isEqualTo(expect),
                () -> assertThat(table.hasOrder()).isFalse()
        );
    }

    @DisplayName("주문 내역이 존재하지 않으면 예외 발생")
    @Test
    void payment_OrderNotExist_ExceptionThrown() {
        assertThatThrownBy(() -> tableService.payment(3, 1))
                .isInstanceOf(OrderNotFoundException.class)
                .hasMessageContaining("주문 내역이 존재하지 않습니다");
    }

    @AfterEach
    void tearDown() {
        List<Table> tables = tableService.getTables();
        for (Table table : tables) {
            table.clear();
        }
    }
}