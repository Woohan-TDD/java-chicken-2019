package domain.table.payment;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ChickenDiscountStrategyTest {
    @DisplayName("치킨 개수를 입력받아 할인 전략 생성")
    @Test
    void constructor() {
        assertThat(new ChickenDiscountStrategy(10))
                .isInstanceOf(DiscountStrategy.class);
    }

    @DisplayName("치킨 할인 적용")
    @CsvSource(value = {"5, 1000000", "10, 990000", "55, 950000", "60, 940000"})
    @ParameterizedTest
    void discount(final int chickenCount, final long expect) {
        DiscountStrategy discountStrategy = new ChickenDiscountStrategy(chickenCount);

        assertThat(discountStrategy.discount(1_000_000)).isEqualTo(expect);
    }
}
