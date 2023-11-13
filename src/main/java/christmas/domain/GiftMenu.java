package christmas.domain;

public class GiftMenu {
    private final String giftName;

    public static final int GIFT_NUMBER = 1;


    public GiftMenu(String giftName {
        this.giftName = giftName;
    }


    public String getGiftName() {
        return giftName;
    }
    public int getGiftNumber() {
        return GIFT_NUMBER;
    }
}
