package domain.menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {
    private static final Menu CHICKEN = new Menu(1, "양념치킨", Category.CHICKEN, 16_000);
    private static final Menu CIDER = new Menu(2, "사이다", Category.BEVERAGE, 2_000);

    @DisplayName("메뉴 생성")
    @Test
    void constructor() {
        assertThat(new Menu(1, "양념치킨", Category.CHICKEN, 16_000)).isInstanceOf(Menu.class);
    }

    @DisplayName("메뉴 번호가 양수가 아닌 경우 예외 발생")
    @Test
    void constructor_NumberIsNotPositive_ExceptionThrown() {
        assertThatThrownBy(() -> new Menu(0, "양념치킨", Category.CHICKEN, 16_000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("메뉴 번호는 양수만 입력 가능합니다");
    }

    @DisplayName("메뉴명이 null인 경우 예외 발생")
    @Test
    void constructor_NameIsNull_ExceptionThrown() {
        assertThatThrownBy(() -> new Menu(1, null, Category.CHICKEN, 16_000))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("메뉴명이 null입니다");
    }

    @DisplayName("메뉴명이 빈 값인 경우 예외 발생")
    @EmptySource
    @ValueSource(strings = "    ")
    @ParameterizedTest
    void constructor_NameIsEmpty_ExceptionThrown(final String name) {
        assertThatThrownBy(() -> new Menu(1, name, Category.CHICKEN, 16_000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("메뉴명은 빈 문자열로만 이루어 질 수 없습니다");
    }

    @DisplayName("가격이 음수인 경우 예외 발생")
    @Test
    void constructor_PriceIsNegative_ExceptionThrown() {
        assertThatThrownBy(() -> new Menu(1, "양념치킨", Category.CHICKEN, -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("가격은 0 또는 양수로 구성되어야 합니다");
    }

    @DisplayName("입력받은 수와 테이블 번호가 같으면 true 반환")
    @Test
    void isSameNumber_NumberIsSame_ReturnTrue() {
        assertThat(CHICKEN.isSameNumber(1)).isTrue();
    }

    @DisplayName("입력받은 수와 테이블 번호가 다르면 false 반환")
    @Test
    void isSameNumber_NumberIsNotSame_ReturnFalse() {
        assertThat(CHICKEN.isSameNumber(2)).isFalse();
    }

    @DisplayName("메뉴가 치킨이면 true 반환")
    @Test
    void isChicken_CategoryIsChicken_ReturnTrue() {
        assertThat(CHICKEN.isChicken()).isTrue();
    }

    @DisplayName("메뉴가 치킨이 아니면 false 반환")
    @Test
    void isChicken_CategoryIsNotChicken_ReturnFalse() {
        assertThat(CIDER.isChicken()).isFalse();
    }
}