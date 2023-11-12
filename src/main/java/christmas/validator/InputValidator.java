package christmas.validator;

import christmas.domain.Menu;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static christmas.domain.Menu.*;
import static christmas.util.Const.*;
import static christmas.util.ValidateMessage.*;

public class InputValidator {

    public static int checkDate(String inputDate) {
        if (!inputDate.matches(INPUT_DATE_TYPE)) {
            throw new IllegalArgumentException(ERROR_DATE_MESSAGE);
        }
        return Integer.parseInt(inputDate);
    }

    public static Map<Menu, Integer> checkMenu(String inputMenu) {
        validatorType(inputMenu);
        validatorBeverage(stringOfMap(inputMenu));
        validatorMaxOrderMenu((stringOfMap(inputMenu)));
        validatorOrderMenuZero((stringOfMap(inputMenu)));
        return stringOfMap(inputMenu);
    }

    private static void validatorType(String inputMenu) {
        String[] menus = inputMenu.split(",");
        for (String menu : menus) {
            if (!menu.matches(INPUT_MENU_TYPE)) {
                throw new IllegalArgumentException(ERROR_MENU_MESSAGE);
            }
        }
    }

    private static Map<Menu, Integer> stringOfMap(String orderMenu) {
        return Arrays.stream(orderMenu.split(","))
                .map(menu -> menu.split("-"))
                .collect(Collectors.toMap(
                        menu -> validatorNoName(menu[ZERO]),
                        menu -> Integer.parseInt(menu[ONE]),
                        (newKey, oldKey) -> {
                            throw new IllegalArgumentException(ERROR_MENU_MESSAGE);
                        }
                ));
    }

    private static Menu validatorNoName(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getName().equals(menuName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_MENU_MESSAGE));
    }

    private static void validatorBeverage(Map<Menu, Integer> orderMenu) {
        boolean beverage = orderMenu.keySet().stream()
                .allMatch(menu -> menu == BEVERAGE_ZERO_COLA || menu == BEVERAGE_RED_WINE || menu == BEVERAGE_CHAMPAGNE);

        if (beverage) {
            throw new IllegalArgumentException(ERROR_BEVERAGE_MESSAGE);
        }
    }


    public static void validatorMaxOrderMenu(Map<Menu, Integer> orderMenu) {
        int total = ZERO;

        for (int quantity : orderMenu.values()) {
            total += quantity;
        }

        if (total > TWENTY) {
            throw new IllegalArgumentException(ERROR_MAX_ORDER_MESSAGE);
        }
    }

    public static void validatorOrderMenuZero(Map<Menu, Integer> orderMenu) {
        for (int value : orderMenu.values()) {
            if (value == ZERO) {
                throw new IllegalArgumentException(ERROR_ZERO_ORDER_MESSAGE);
            }
        }
    }


}
