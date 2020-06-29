package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaymentTypeTest {

    private Orders orders;

    @BeforeEach
    void setUp() {
        orders = new Orders();
        orders.orderMenu(new Order(MenuRepository.findMenuByNumber(1), new Count(9)));
        orders.orderMenu(new Order(MenuRepository.findMenuByNumber(21), new Count(10)));
    }

    @Test
    @DisplayName("입력에 따른 결제방식 반환")
    void of() {
        assertThat(PaymentType.of(1)).isEqualTo(PaymentType.CARD);
        assertThat(PaymentType.of(2)).isEqualTo(PaymentType.CASH);
    }

    @Test
    @DisplayName("유효하지 않은 결제방식 예외처리")
    void validateOf() {
        assertThatThrownBy(() -> PaymentType.of(3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 결제방식입니다.");
    }

    @Test
    @DisplayName("카드결제 최종 결제금액 반환")
    void calculateCard() {
        assertThat(PaymentType.CARD.calculate(orders)).isEqualTo(154000);
    }

    @Test
    @DisplayName("현금결제 최종 결제금액 반환")
    void calculateCash() {
        assertThat(PaymentType.CASH.calculate(orders)).isEqualTo(154000 * 0.95);
    }

    @Test
    @DisplayName("카드인경우 치킨 10마리당 만원할인")
    void discountChickenCard() {
        orders.orderMenu(new Order(MenuRepository.findMenuByNumber(1), new Count(1)));
        assertThat(PaymentType.CARD.calculate(orders)).isEqualTo(170000 - 10000);
    }

    @Test
    @DisplayName("현금인경우 치킨 10마리당 만원할인")
    void discountChickenCash() {
        orders.orderMenu(new Order(MenuRepository.findMenuByNumber(1), new Count(1)));
        assertThat(PaymentType.CASH.calculate(orders)).isEqualTo(160000 * 0.95);
    }

}