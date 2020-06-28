package domain.type;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PaymentTypeTest {

    @ParameterizedTest
    @CsvSource(value = {"1:CARD", "2:CASH"}, delimiter = ':')
    void find(int number, PaymentType type) {
        assertThat(PaymentType.find(number)).isEqualTo(type);
    }

    @Test
    void find_Exception() {
        assertThatThrownBy(() -> PaymentType.find(3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("원하는 결제 방식이 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"CARD:10000", "CASH:9500"}, delimiter = ':')
    void payment(PaymentType type, long finalAmount) {
        assertThat(type.payment(10000)).isEqualTo(finalAmount);
    }
}