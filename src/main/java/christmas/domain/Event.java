package christmas.domain;

import java.time.LocalDate;

import static christmas.domain.Menu.*;
import static christmas.util.Const.*;

public class Event {

    private static final LocalDate START = LocalDate.of(2023, 12, 1);
    private static final LocalDate END = LocalDate.of(2023, 12, 31);

    private final Order order;

    private final Date date;

    public Event(Order order, Date date) {
        this.order = order;
        this.date = date;
    }

    public GiftMenu giftMenu() {
        if (order.calculateTotalPrice() >= GiftMenuAmount) {
         return new GiftMenu(getGiftMenuName(),getGiftMenuPrice());
        }
        return null;
    }



    public Order getOrder() {
        return order;
    }

    public Date getDate() {
        return date;
    }



}
