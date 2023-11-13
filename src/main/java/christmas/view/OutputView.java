package christmas.view;


import christmas.domain.*;


import java.util.Map;

import static christmas.util.Message.*;

public class OutputView {



    public static void printResult(Event event) {
        System.out.println("12월 "+event.getDate().getOrderDate()+"일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");

        System.out.println(ORDER_MENU);
        System.out.println(event.getOrder().toString());

        System.out.println(BEFORE_TOTAL_PRICE);
        printBeforeTotalPrice(event);

        System.out.println(GIFT_MENU);
        giftMenu(event);

        System.out.println(BENEFITS_DETAILS);

    }

    private static void printBeforeTotalPrice(Event event){
        int beforeTotalPrice = event.getOrder().calculateTotalPrice();
        System.out.printf("%,d원\n%n",beforeTotalPrice);
    }


    private static void giftMenu(Event event){
        GiftMenu giftMenu = event.giftMenu();
        if(giftMenu!=null){
            System.out.println(giftMenu.getGiftName() + " " + giftMenu.getGiftNumber() + "개\n");
            return;
        }
        printNo();
    }
    
    private static void printNo(){
        System.out.println(NONE);
    }






}
