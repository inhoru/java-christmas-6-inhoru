package christmas.domain;

import java.util.Map;

import static christmas.util.Const.ZERO;
import static christmas.validator.InputValidator.*;


public class Order {

    private final Map<Menu, Integer> orderMenu;

    public Order(String inputMenu) {
        this.orderMenu = checkMenu(inputMenu);
    }


    public int calculateTotalPrice() {
        int totalPrice = ZERO;
        for (Menu menu : orderMenu.keySet()) {
            int number = orderMenu.get(menu);
            int menuPrice = menu.getPrice();
            totalPrice += menuPrice * number;
        }
        return totalPrice;
    }


    public Map<Menu, Integer> getOrderMenu() {
        return orderMenu;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Menu, Integer> entry : orderMenu.entrySet()) {
            Menu menu = entry.getKey();
            int number = entry.getValue();
            sb.append(menu.getName()).append(" ").append(number).append("개\n");
        }
        return sb.toString();
    }
}

