package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EventTest {

    @DisplayName("이벤트 기간내 할인 전 총주문 금액이 12만원 이상일 때 증정 테스트")
    @Test
    void giftMenuTest() {
        // given
        Order order = new Order("해산물파스타-2,레드와인-1,초코케이크-1");
        Date date = new Date("3");
        Event event = new Event(order, date);

        // when
        GiftMenu giftMenu = event.giftMenu();

        // then
        assertThat(giftMenu).isNotNull();
    }

    @DisplayName("할인 전 총 주문금액 테스트")
    @Test
    void beforeTotalPriceTest() {
        // given
        Order order = new Order("타파스-1,제로콜라-1");

        // when
        int beforeTotalPrice = order.calculateTotalPrice();

        // then
        assertThat(beforeTotalPrice).isEqualTo(8500);
    }

    @DisplayName("D_DAY 이벤트 할인 테스트")
    @Test
    void dDayEventTest() {
        // given
        Order order = new Order("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        Date date = new Date("3");
        Event event = new Event(order, date);

        // when
        Discount discount = new Discount(event);
        int dDay = discount.calculateDday(event);

        // then
        assertThat(dDay).isEqualTo(1200);
    }

    @DisplayName("평일 할인 이벤트 테스트")
    @Test
    void weekDayTest() {
        // given
        Order order = new Order("초코케이크-2,제로콜라-1");
        Date date = new Date("5");
        Event event = new Event(order, date);
        Discount discount = new Discount(event);

        // when
        int week = discount.calculateWeek(event);

        // then
        assertThat(week).isEqualTo(4046);
    }

    @DisplayName("주말 할인 이벤트 테스트")
    @Test
    void weekendDayTest() {
        // given
        Order order = new Order("티본스테이크-3,초코케이크-1");
        Date date = new Date("8");
        Event event = new Event(order, date);
        Discount discount = new Discount(event);

        // when
        int week = discount.calculateWeekend(event);

        // then
        assertThat(week).isEqualTo(6069);
    }

    @DisplayName("특별할인 이벤트 테스트")
    @Test
    void specialDayTest() {
        // given
        Order order = new Order("양송이수프-2,타파스-1,아이스크림-1,해산물파스타-1");
        Date date = new Date("31");
        Event event = new Event(order, date);

        // when
        Discount discount = new Discount(event);
        int special = discount.calculateSpecialDay(event);

        // then
        assertThat(special).isEqualTo(1000);
    }
}

