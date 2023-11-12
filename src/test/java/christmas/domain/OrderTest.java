package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderTest {
    @DisplayName("유효하지 않은 주문 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"티비-1,티삼-2", "타파스1,제로콜라2", "타파스-1 제로콜라-3", "타파스-0,제로콜라-1"})
    void testOrderMenuValidate(String input) {
        assertThatThrownBy(() -> new Order(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @DisplayName("음료만 주문 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"제로콜라-1,레드와인-2","제로콜라-3","샴페인-1,제로콜라-1,레드와인-1"})
    void testOrderMenuBeverage(String input) {
        assertThatThrownBy(() -> new Order(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴 주문시 20개 이하 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-15,타파스-1,시저샐러드-5", "양송이수프-0,타파스-0,시저샐러드-0"})
    void testOrderMenuOfRange(String input) {
        assertThatThrownBy(() -> new Order(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @DisplayName("메뉴 주문시 0개 입력 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-0,타파스-0,시저샐러드-0"})
    void testOrderMenuOfZero(String input) {
        assertThatThrownBy(() -> new Order(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @DisplayName("메뉴 주문시 중복 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-2,양송이수프-2,시저샐러드-1"})
    void testOrderMenuDuplication(String input) {
        assertThatThrownBy(() -> new Order(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
