package domain.table.payment;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiscountStrategiesTest {
    private static final DiscountStrategy CHICKEN_DISCOUNT_STRATEGY = new ChickenDiscountStrategy(10);
    private static final DiscountStrategy CASH_DISCOUNT_STRATEGY = PaymentMethod.CASH;

    @DisplayName("여러가지 할인 정책을 가지는 전략 인스턴스 생성")
    @Test
    void constructor() {
        List<DiscountStrategy> discountStrategies = Arrays.asList(CHICKEN_DISCOUNT_STRATEGY, CASH_DISCOUNT_STRATEGY);
        assertThat(new DiscountStrategies(discountStrategies)).isInstanceOf(DiscountStrategy.class);
    }

    @DisplayName("여러가지 할인 정책을 순서에 따라 모두 적용")
    @Test
    void discount() {
        DiscountStrategies discountStrategies = new DiscountStrategies(
                Arrays.asList(CHICKEN_DISCOUNT_STRATEGY, CASH_DISCOUNT_STRATEGY));

        assertThat(discountStrategies.discount(50_000)).isEqualTo(38_000);
    }
}
