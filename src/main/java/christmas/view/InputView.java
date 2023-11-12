package christmas.view;

import camp.nextstep.edu.missionutils.Console;

import static christmas.util.Message.*;

public class InputView {

    public String inputDate() {
        System.out.println(INPUT_DATE);
        return Console.readLine();
    }
    public String inputMenu(){
        System.out.println(INPUT_MENU);
        return Console.readLine();
    }

}
