package christmas.controller;


import christmas.domain.OrderDate;
import christmas.view.InputView;

import static christmas.validator.InputValidator.*;

public class OrderController {
    public void start(){
        OrderDate orderDate = getDate();


    }

    private OrderDate getDate(){
        InputView inputView = new InputView();
        try {
            return new OrderDate(inputView.readDate());
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return getDate();
        }
    }
}
