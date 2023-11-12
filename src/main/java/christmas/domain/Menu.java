package christmas.domain;

public enum Menu {
    APPETIZER_YANG_SONGI_SOUP("양송이수프", 6000),
    APPETIZER_TAPAS("타파스", 5500),
    APPETIZER_CAESAR_SALAD("시저샐러드", 8000),

    MAIN_TBONE_STEAK("티본스테이크", 55000),
    MAIN_BBQ_RIB("바비큐립", 54000),
    MAIN_SEAFOOD_PASTA("해산물파스타", 35000),
    MAIN_CHRISTMAS_PASTA("크리스마스파스타", 25000),
    DESSERT_CHOCO_CAKE("초코케이크", 15000),
    DESSERT_ICE_CREAM("아이스크림", 5000),

    BEVERAGE_ZERO_COLA("제로콜라", 3000),
    BEVERAGE_RED_WINE("레드와인", 60000),
    BEVERAGE_CHAMPAGNE("샴페인", 25000),
    EMPTY("",0);





    private final String menuName;
    private final int price;


    Menu(String menuName, int price) {
        this.menuName = menuName;
        this.price = price;
    }


    public String getName() {
        return menuName;
    }
    public int getPrice() {
        return price;
    }
}
