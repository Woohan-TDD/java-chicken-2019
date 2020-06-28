package domain.type;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FunctionTypeTest {
    @ParameterizedTest
    @CsvSource(value = {"1:ONE", "2:TWO", "3:THREE"}, delimiter = ':')
    void find(int input, FunctionType type) {
        FunctionType functionType = FunctionType.find(input);
        assertThat(functionType).isEqualTo(type);
    }

    @Test
    void find_Exception() {
        assertThatThrownBy(() -> FunctionType.find(4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1 ~ 3사이의 숫자만 가능합니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"1:true", "2:true", "3:false"}, delimiter = ':')
    void isNotThree(int number, boolean expect) {
        FunctionType functionType = FunctionType.find(number);
        assertThat(functionType.isNotThree()).isEqualTo(expect);
    }
}