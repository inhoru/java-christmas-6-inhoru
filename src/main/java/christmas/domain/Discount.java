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




}
