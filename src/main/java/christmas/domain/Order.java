package christmas.domain;

import java.util.Map;

import static christmas.validator.InputValidator.*;


public class Order {

    private final Map<Menu, Integer> orderMenu;

    public Order(String inputMenu) {
        this.orderMenu =  checkMenu(inputMenu);
    }


    public Map<Menu, Integer> getOrderMenu() {
        return orderMenu;
    }

}
