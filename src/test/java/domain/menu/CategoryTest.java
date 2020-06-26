package domain.menu;

import static domain.menu.Category.BEVERAGE;
import static domain.menu.Category.CHICKEN;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CategoryTest {
    @DisplayName("치킨 카테고리에 속하면 true 반환")
    @Test
    void isChicken_CategoryIsChicken_ReturnTrue() {
        assertThat(CHICKEN.isChicken()).isTrue();
    }

    @DisplayName("치킨 카테고리에 속하지 않으면 false 반환")
    @Test
    void isChicken_CategoryIsNotChicken_ReturnFalse() {
        assertThat(BEVERAGE.isChicken()).isFalse();
    }
}
