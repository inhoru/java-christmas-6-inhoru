package christmas.domain;

import static christmas.validator.InputValidator.checkDate;

public class OrderDate {
    private final int orderDate;
    public OrderDate(String orderDate) {
        this.orderDate = checkDate(orderDate);
    }

    public int getOrderDate() {
        return orderDate;
    }

}
