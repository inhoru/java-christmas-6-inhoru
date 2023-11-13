package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static christmas.util.Const.*;

public class Discount {
    private static final LocalDate START = LocalDate.of(2023, 12, 1);
    private static final LocalDate END = LocalDate.of(2023, 12, 25);

    private static final int D_DAY_START_AMOUNT = 1000;
    private static final int D_DAY_INCREASE_AMOUNT = 100;
    private static final int WEEKDAY_DISCOUNT_DESSERT = 2023;
    private static final int WEEKEND_DISCOUNT_MAIN = 2023;
    private static final int SPECIAL_DISCOUNT = 1000;

    private final int dDayDiscount;
    private final int weekdayDiscount;
    private final int weekendDiscount;
    private final int specialDiscount;


    public Discount(Event event) {
        int orderDate = event.getDate().getOrderDate();
        this.dDayDiscount = calculateDday(orderDate);
        this.weekdayDiscount = calculateWeek(event);
        this.weekendDiscount = calculateWeekend(event);
        this.specialDiscount = calculateSpecialDay(orderDate);
    }

    private static int calculateDday(int orderDate) {
        if (orderDate >= 1 && orderDate <= 25) {
            return D_DAY_START_AMOUNT + (D_DAY_INCREASE_AMOUNT * orderDate);
        }
        return ZERO;
    }

    private static int calculateWeek(Event event) {
        int orderDate = event.getDate().getOrderDate();
        Map<Menu, Integer> orderMenu = event.getOrder().getOrderMenu();
        LocalDate orderLocalDate = LocalDate.of(2023, 12, orderDate);

        int totalDiscount = 0;
        for (Map.Entry<Menu, Integer> entry : orderMenu.entrySet()) {
            Menu menu = entry.getKey();
            if (weekday(orderLocalDate) && menu.name().contains("DESSERT")) {
                totalDiscount += WEEKDAY_DISCOUNT_DESSERT;
            }
        }

        return totalDiscount;
    }

    private static boolean weekday(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY;
    }

    private static int calculateWeekend(Event event) {
        int orderDate = event.getDate().getOrderDate();
        Map<Menu, Integer> orderMenu = event.getOrder().getOrderMenu();
        LocalDate orderLocalDate = LocalDate.of(2023, 12, orderDate);

        int totalDiscount = ZERO;
        for (Map.Entry<Menu, Integer> entry : orderMenu.entrySet()) {
            Menu menu = entry.getKey();
            if (weekend(orderLocalDate) && menu.name().contains("MAIN")) {
                totalDiscount += WEEKEND_DISCOUNT_MAIN;
            }
        }

        return totalDiscount;
    }

    private static boolean weekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    private static int calculateSpecialDay(int orderDay) {
        LocalDate orderLocalDate = LocalDate.of(2023, 12, orderDay);
        DayOfWeek dayOfWeek = orderLocalDate.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SUNDAY) {
            return SPECIAL_DISCOUNT;

        }
        return ZERO;


    }
}
