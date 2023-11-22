package christmas.controller;


import christmas.domain.*;
import christmas.view.InputView;

import static christmas.view.OutputView.printResult;


public class OrderController {
    InputView inputView = new InputView();

    public void start() {
        Date orderDate = getDate();
        Order order = getMenu();

        Event event = new Event(order, orderDate);
        Discount discount = new Discount(event);

        String badge = getEventBadge(discount.calculateTotalBenefits());

        printResult(event, discount, badge);

    }

    private Date getDate() {
        try {
            return new Date(inputView.readDate());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getDate();
        }
    }

    private Order getMenu() {
        try {
            return new Order(inputView.inputMenu());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getMenu();
        }
    }

    private String getEventBadge(int totalBenefit) {
        return new EventBadge(totalBenefit).getEventBadge();
    }

}
