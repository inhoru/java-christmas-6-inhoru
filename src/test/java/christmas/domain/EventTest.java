package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

import static christmas.util.Const.*;
import static org.assertj.core.api.Assertions.assertThat;

public class EventTest {

    @DisplayName("이벤트 기간내 할인 전 총주문 금액이 12만원 이상일 때 증정 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"})
    void giftMenuTest(String orderDate) {
        // given
        Order order = new Order("해산물파스타-2,레드와인-1,초코케이크-1");
        Date date = new Date(orderDate);
        Event event = new Event(order, date);
        // when
        GiftMenu giftMenu = event.giftMenu();

        // then
        assertThat(giftMenu).isNotNull();
    }

    @DisplayName("하나만 주문했을 때 메뉴의 가격이 총 주문 금액과 일치 테스트")
    @Test
    void testTotalPriceWithSingleMenu() {
        // given
        Order order = new Order("양송이수프-1");

        // when
        int totalPrice = order.calculateTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(Menu.APPETIZER_YANG_SONGI_SOUP.getPrice());
    }

    @DisplayName("여러 메뉴를 주문했을 때 총 주문 금액이 예상한 값과 일치 테스트")
    @Test
    void testTotalPriceWithMultipleMenus() {
        // given
        Order order = new Order("양송이수프-1,레드와인-2,초코케이크-1");

        // when
        int totalPrice = order.calculateTotalPrice();

        // then
        int expectedTotalPrice = Menu.APPETIZER_YANG_SONGI_SOUP.getPrice()
                + Menu.BEVERAGE_RED_WINE.getPrice() * 2
                + Menu.DESSERT_CHOCO_CAKE.getPrice();
        assertThat(totalPrice).isEqualTo(expectedTotalPrice);
    }

    @DisplayName("D_DAY 이벤트 할인 테스트")
    @Test
    void dDayEventTest() {
        // given
        for (int i = 1; i <= 31; i++) {
            Order order = new Order("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            Date date = new Date(String.valueOf(i));
            Event event = new Event(order, date);

            // when
            Discount discount = new Discount(event);
            int dDay = discount.calculateDday(event);

            // then
            int increaseAmount = 0;
            if (i <= 25) {
                increaseAmount = (i - 1) * 100 + 1000;
            }
            assertThat(dDay).isEqualTo(increaseAmount);
        }
    }

    @DisplayName("평일 할인 이벤트 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"})
    void weekDayTest(String orderDate) {
        // given
        Order order = new Order("초코케이크-2,제로콜라-1");
        Date date = new Date(orderDate);
        Event event = new Event(order, date);
        LocalDate orderLocalDate = LocalDate.of(2023, 12, date.getOrderDate());
        Discount discount = new Discount(event);

        // when
        int totalDiscount = calculateTotalWeekdayDiscount(orderLocalDate, event);
        int week = discount.calculateWeek(event);

        // then
        assertThat(week).isEqualTo(totalDiscount);
    }

    private int calculateTotalWeekdayDiscount(LocalDate orderLocalDate, Event event) {
        int totalDiscount = ZERO;
        Map<Menu, Integer> orderMenu = event.getOrder().getOrderMenu();
        for (Map.Entry<Menu, Integer> entry : orderMenu.entrySet()) {
            totalDiscount += calculateWeekdayDiscount(orderLocalDate, entry);
        }
        return totalDiscount;
    }

    private int calculateWeekdayDiscount(LocalDate orderLocalDate, Map.Entry<Menu, Integer> entry) {
        Menu menu = entry.getKey();
        int number = entry.getValue();
        int discount = ZERO;
        if (weekday(orderLocalDate) && menu.getCategory().equals("dessert")) {
            for (int i = 0; i < number; i++) {
                discount += WEEKDAY_DISCOUNT_DESSERT;
            }
        }
        return discount;
    }

    private static boolean weekday(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.FRIDAY;
    }


    @DisplayName("주말 할인 이벤트 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"})
    void weekEndTest(String orderDate) {
        // given
        Order order = new Order("초코케이크-2,제로콜라-1,티본스테이크-2");
        Date date = new Date(orderDate);
        Event event = new Event(order, date);
        LocalDate orderLocalDate = LocalDate.of(2023, 12, date.getOrderDate());
        Discount discount = new Discount(event);

        // when
        int totalDiscount = calculateTotalWeekEnbDiscount(orderLocalDate, event);
        int weekEnd = discount.calculateWeekend(event);

        // then
        assertThat(weekEnd).isEqualTo(totalDiscount);
    }

    private int calculateTotalWeekEnbDiscount(LocalDate orderLocalDate, Event event) {
        int totalDiscount = ZERO;
        Map<Menu, Integer> orderMenu = event.getOrder().getOrderMenu();
        for (Map.Entry<Menu, Integer> entry : orderMenu.entrySet()) {
            totalDiscount += calculateWeekEndDiscount(orderLocalDate, entry);
        }
        return totalDiscount;
    }

    private int calculateWeekEndDiscount(LocalDate orderLocalDate, Map.Entry<Menu, Integer> entry) {
        Menu menu = entry.getKey();
        int number = entry.getValue();
        int discount = ZERO;

        if (weekEnd(orderLocalDate) && menu.getCategory().equals("main")) {
            for (int i = 0; i < number; i++) {
                discount += WEEKEND_DISCOUNT_MAIN;
            }
        }
        return discount;
    }

    private static boolean weekEnd(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.FRIDAY;
    }


    @DisplayName("특별할인 이벤트 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"})
    void specialDayTest(String orderDate) {
        // given
        Order order = new Order("양송이수프-2,타파스-1,아이스크림-1,해산물파스타-1");
        Date date = new Date(orderDate);
        Event event = new Event(order, date);
        Discount discount = new Discount(event);
        // when
        int special = discount.calculateSpecialDay(event);

        // then
        assertThat(special).isEqualTo(specialday(event));
    }
    private int specialday(Event event){
        int orderDate = event.getDate().getOrderDate();
        LocalDate orderLocalDate = LocalDate.of(2023, 12, orderDate);
        DayOfWeek dayOfWeek = orderLocalDate.getDayOfWeek();
        if (orderDate == 25 || dayOfWeek == DayOfWeek.SUNDAY) {
            return SPECIAL_DISCOUNT;
        }
        return ZERO;
    }



}

