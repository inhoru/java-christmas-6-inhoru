package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

import static christmas.util.Const.*;
import static christmas.util.Message.*;

public class Discount {
    private final int dDayDiscount;
    private final int weekdayDiscount;
    private final int weekendDiscount;
    private final int specialDiscount;
    private final int giftMenuDiscount;


    public Discount(Event event) {
        this.dDayDiscount = calculateDday(event);
        this.specialDiscount = calculateSpecialDay(event);
        this.weekdayDiscount = calculateWeek(event);
        this.weekendDiscount = calculateWeekend(event);
        this.giftMenuDiscount = calculateGiftMenu(event);
    }

    public int calculateDday(Event event) {
        int orderDate = event.getDate().getOrderDate();
        int totalPrice = event.getOrder().calculateTotalPrice();
        if (orderDate >= 1 && orderDate <= 25 && totalPrice >= 10000) {
            int increase = D_DAY_INCREASE_AMOUNT * (orderDate - 1);
            return D_DAY_START_AMOUNT + increase;
        }
        return ZERO;
    }


    public int calculateWeek(Event event) {
        int orderDate = event.getDate().getOrderDate();
        int totalPrice = event.getOrder().calculateTotalPrice();
        Map<Menu, Integer> orderMenu = event.getOrder().getOrderMenu();
        LocalDate orderLocalDate = LocalDate.of(2023, 12, orderDate);
        int totalDiscount = ZERO;

        for (Map.Entry<Menu, Integer> entry : orderMenu.entrySet()) {
            totalDiscount += calculateWeekdayDiscount(orderLocalDate, entry, totalPrice);
        }

        return totalDiscount;
    }

    public int calculateWeekdayDiscount(LocalDate orderLocalDate, Map.Entry<Menu, Integer> entry, int totalPrice) {
        Menu menu = entry.getKey();
        int number = entry.getValue();
        int discount = ZERO;

        if (weekday(orderLocalDate) && menu.getCategory().equals("dessert") && totalPrice >= 10000) {
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

    public int calculateWeekend(Event event) {
        int orderDate = event.getDate().getOrderDate();
        Map<Menu, Integer> orderMenu = event.getOrder().getOrderMenu();
        int totalPrice = event.getOrder().calculateTotalPrice();
        LocalDate orderLocalDate = LocalDate.of(2023, 12, orderDate);
        int totalDiscount = ZERO;

        for (Map.Entry<Menu, Integer> entry : orderMenu.entrySet()) {
            totalDiscount += calculateWeekendDiscount(orderLocalDate, entry, totalPrice);
        }

        return totalDiscount;
    }

    public int calculateWeekendDiscount(LocalDate orderLocalDate, Map.Entry<Menu, Integer> entry, int totalPrice) {
        Menu menu = entry.getKey();
        int number = entry.getValue();
        int discount = ZERO;

        if (weekend(orderLocalDate) && menu.getCategory().equals("main") && totalPrice >= 10000) {
            for (int i = 0; i < number; i++) {
                discount += WEEKEND_DISCOUNT_MAIN;
            }
        }

        return discount;
    }


    private static boolean weekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.FRIDAY;
    }

    public int calculateSpecialDay(Event event) {
        int orderDate = event.getDate().getOrderDate();
        int totalPrice = event.getOrder().calculateTotalPrice();
        LocalDate orderLocalDate = LocalDate.of(2023, 12, orderDate);
        DayOfWeek dayOfWeek = orderLocalDate.getDayOfWeek();
        if (orderDate == 25 || dayOfWeek == DayOfWeek.SUNDAY && totalPrice >= 10000) {
            return SPECIAL_DISCOUNT;

        }
        return ZERO;


    }

    private static int calculateGiftMenu(Event event) {
        GiftMenu giftMenu = event.giftMenu();
        if (giftMenu != null) {
            return giftMenu.getGiftPrice();
        }
        return ZERO;
    }

    public int calculateTotalBenefits() {
        return dDayDiscount + weekdayDiscount + weekendDiscount + specialDiscount + giftMenuDiscount;
    }

    public int calculateEstimatedAmount() {
        return dDayDiscount + weekdayDiscount + weekendDiscount + specialDiscount;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        appendResult(result, D_DAY_DISCOUNT, dDayDiscount);
        appendResult(result, WEEKDAY_DISCOUNT, weekdayDiscount);
        appendResult(result, WEEKEND_DISCOUNT, weekendDiscount);
        appendResult(result, SPECIAL, specialDiscount);
        appendResult(result, GIFT_DISCOUNT, giftMenuDiscount);
        return result.toString();
    }

    private void appendResult(StringBuilder result, String name, int discount) {
        if (discount != ZERO) {
            result.append(name).append(": -").append(String.format("%,d원", discount)).append("\n");
        }
    }


}
