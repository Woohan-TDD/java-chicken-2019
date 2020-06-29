package domain.menu;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CategoryTest {
    @DisplayName("치킨 카테고리에 속하면 true 반환")
    @CsvSource(value = {"CHICKEN,true", "BEVERAGE,false"})
    @ParameterizedTest
    void isChicken(final Category category, final boolean expect) {
        assertThat(category.isChicken()).isEqualTo(expect);
    }
}
