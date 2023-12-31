package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DateTest {

    @DisplayName("잘못된 유형 날짜 입력 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"0", "32", "77", "asd", "-3", "!@#"})
    void testOrderDateType(String input) {
        assertThatThrownBy(() -> new Date(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
