package christmas.view;


import christmas.domain.Date;
import christmas.domain.Menu;
import christmas.domain.Order;

import java.util.List;
import java.util.Map;


import static christmas.util.Message.*;

public class OutputView {



    public static void printResult(Date orderdate, Order order) {
        System.out.println("12월 "+orderdate.getOrderDate()+"일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");

        System.out.println(ORDER_MENU);
        System.out.println(order.toString());

        System.out.println(BEFORE_TOTAL_PRICE);
        System.out.println(String.format("%,d원",   order.calculateTotalPrice()));


    }



}
