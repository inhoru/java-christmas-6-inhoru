package christmas.domain;

public class GiftMenu {
    private final String giftName;
    private final int giftPrice;

    public static final int GIFT_NUMBER = 1;


    public GiftMenu(String giftName, int giftPrice) {
        this.giftName = giftName;
        this.giftPrice = giftPrice;
    }


    public int getGiftPrice() {
        return giftPrice;
    }

    public String getGiftName() {
        return giftName;
    }

    public int getGiftNumber() {
        return GIFT_NUMBER;
    }
}
