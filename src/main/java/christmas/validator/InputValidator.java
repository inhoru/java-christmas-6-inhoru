package christmas.validator;

import static christmas.util.ValidateMessage.*;

public class InputValidator {

    public static int checkDate(String inputDate) {
        if (!inputDate.matches(INPUT_DATE_TYPE)) {
            throw new IllegalArgumentException(ERROR_DATE_MESSAGE);
        }
        return Integer.parseInt(inputDate);

    }
}
