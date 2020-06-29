package domain.table.payment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PaymentMethodTest {
    @DisplayName("ofNumber: 숫자에 맞는 결제 타입을 반환")
    @CsvSource(value = {"1,CREDIT_CARD", "2,CASH"})
    @ParameterizedTest
    void ofNumber(final int number, final PaymentMethod expect) {
        PaymentMethod actual = PaymentMethod.ofNumber(number);

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("ofNumber: 숫자에 맞는 결제 타입을 찾지 못하면 예외 발생")
    @Test
    void ofNumber_NumberNotFound_ExceptionThrown() {
        assertThatThrownBy(() -> PaymentMethod.ofNumber(3))
                .isInstanceOf(PaymentMethodNotFoundException.class)
                .hasMessageContaining("결제 방식이 존재하지 않습니다");
    }

    @DisplayName("applyDiscount: 할인을 적용")
    @CsvSource(value = {"CREDIT_CARD,10000", "CASH,9500"})
    @ParameterizedTest
    void applyDiscount(final PaymentMethod paymentMethod, final int expect) {
        long actual = paymentMethod.discount(10_000);

        assertThat(actual).isEqualTo(expect);
    }
}