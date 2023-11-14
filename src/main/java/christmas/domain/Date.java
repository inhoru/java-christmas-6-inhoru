package christmas.domain;

import static christmas.validator.InputValidator.checkDate;

public class Date {
    private final int orderDate;

    public Date(String orderDate) {
        this.orderDate = checkDate(orderDate);
    }

    public int getOrderDate() {
        return orderDate;
    }

}
