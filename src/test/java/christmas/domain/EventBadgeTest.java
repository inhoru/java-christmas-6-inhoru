package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EventBadgeTest {
    @DisplayName("총혜택 금액에 따른 이벤트 배지 테스트")
    @Test
    void eventBadgeTest() {
        // given
        Order order = new Order("해산물파스타-2,레드와인-1,초코케이크-1");
        Date date = new Date("20");
        Event event = new Event(order, date);
        Discount discount = new Discount(event);
        int benefitsTotal = discount.calculateTotalBenefits();

        // when
        EventBadge eventBadge = new EventBadge(benefitsTotal);
        String badge = eventBadge.getEventBadge();

        // then
        assertThat(badge).isEqualTo("산타");
    }
}
