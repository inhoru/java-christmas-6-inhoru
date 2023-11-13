package christmas.domain;

import java.util.Map;

import static christmas.util.Const.ZERO;

public enum Menu {
    APPETIZER_YANG_SONGI_SOUP("양송이수프","appetizer", 6000),
    APPETIZER_TAPAS("타파스", "appetizer",5500),
    APPETIZER_CAESAR_SALAD("시저샐러드", "appetizer",8000),

    MAIN_TBONE_STEAK("티본스테이크", "main",55000),
    MAIN_BBQ_RIB("바비큐립","main", 54000),
    MAIN_SEAFOOD_PASTA("해산물파스타","main", 35000),
    MAIN_CHRISTMAS_PASTA("크리스마스파스타","main", 25000),
    DESSERT_CHOCO_CAKE("초코케이크", "dessert",15000),
    DESSERT_ICE_CREAM("아이스크림","dessert", 5000),

    BEVERAGE_ZERO_COLA("제로콜라", "beverage",3000),
    BEVERAGE_RED_WINE("레드와인", "beverage",60000),
    BEVERAGE_CHAMPAGNE("샴페인", "beverage",25000),
    EMPTY("", "",0);


    private final String menuName;
    private final String category;
    private final int price;



    Menu(String menuName, String category,int price) {
        this.menuName = menuName;
        this.category = category;
        this.price = price;
    }

    public static String getGiftMenuName() {
        return BEVERAGE_CHAMPAGNE.menuName;
    }public static int getGiftMenuPrice() {
        return BEVERAGE_CHAMPAGNE.price;
    }


    public String getName() {
        return menuName;
    }

    public int getPrice() {
        return price;
    }
    public String getCategory() {
        return category;
    }


}
