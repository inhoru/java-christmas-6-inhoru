package christmas.controller;


import christmas.domain.Date;
import christmas.domain.Order;
import christmas.view.InputView;

import static christmas.view.OutputView.printResult;


public class OrderController {
    InputView inputView = new InputView();

    public void start() {
        Date orderDate = getDate();
        Order order = getMenu();

        printResult(orderDate,order);




    }

    private Date getDate() {
        try {
            return new Date(inputView.inputDate());
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

}
