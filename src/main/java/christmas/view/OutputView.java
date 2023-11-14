package christmas.view;


import christmas.domain.*;


import java.util.Map;

import static christmas.util.Message.*;

public class OutputView {



    public static void printResult(Event event,Discount discount) {
        printMenu(event);

        printBeforeTotalPrice(event);

        printGiftMenu(event);

        printBenefitsDetails(discount);

        printBenefitsTotalPrice(discount);

    }

    private static void printMenu(Event event){
        System.out.println("12월 "+event.getDate().getOrderDate()+"일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
        System.out.println(ORDER_MENU);
        System.out.println(event.getOrder().toString());
    }
    private static void printBeforeTotalPrice(Event event){
        System.out.println(BEFORE_TOTAL_PRICE);
        int beforeTotalPrice = event.getOrder().calculateTotalPrice();
        System.out.printf("%,d원\n%n",beforeTotalPrice);
    }



    private static void printBenefitsDetails(Discount discount){
        System.out.println(BENEFITS_DETAILS);
        System.out.println(discount.toString());
    }

    private static void printGiftMenu(Event event){
        System.out.println(GIFT_MENU);
        GiftMenu giftMenu = event.giftMenu();
        if(giftMenu!=null){
            System.out.println(giftMenu.getGiftName() + " " + giftMenu.getGiftNumber() + "개\n");
            return;
        }
        printNo();
    }

    private static void  printBenefitsTotalPrice(Discount discount){
        System.out.println(BENEFITS_TOTAL_PRICE);
        System.out.println(discount.calculateTotalBenefits());
    }


    
    private static void printNo(){
        System.out.println(NONE);
    }






}
