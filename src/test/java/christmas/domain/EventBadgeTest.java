package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static christmas.util.Const.*;
import static org.assertj.core.api.Assertions.assertThat;

public class EventBadgeTest {
    @DisplayName("총혜택 금액에 따른 이벤트 배지 테스트")
    @ParameterizedTest
    @CsvSource({
            "5, 해산물파스타-1,초코케이크-1",
            "24, 양송이수프-2,타파스-1,아이스크림-1,해산물파스타-1",
            "31, 아이스크림-1",
            "27, 타파스-1,제로콜라-3"
    })
    void eventBadgeTest(String orderDate, String orderItems) {
        // given
        Order order = new Order(orderItems);
        Date date = new Date(orderDate);
        Event event = new Event(order, date);
        Discount discount = new Discount(event);
        int benefitsTotal = discount.calculateTotalBenefits();

        // when
        EventBadge eventBadge = new EventBadge(benefitsTotal);
        String badge = eventBadge.getEventBadge();

        // then
        assertThat(badge).isEqualTo(calculateBadge(benefitsTotal));
    }

    public static String calculateBadge(int totalBenefit) {
        if (totalBenefit >= EVENT_BADGE_PRICE_SANTA) return SANTA;
        if (totalBenefit >= EVENT_BADGE_PRICE_TREE) return TREE;
        if (totalBenefit >= EVENT_BADGE_PRICE_STARS) return STARS;
        return EMPTY;
    }
}
