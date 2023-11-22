package christmas.domain;

import static christmas.util.Const.*;

public class EventBadge {
    private final String eventBadge;

    public EventBadge(int totalBenefit) {
        this.eventBadge = calculateBadge(totalBenefit);
    }

    public static String calculateBadge(int totalBenefit) {
        if (totalBenefit >= EVENT_BADGE_PRICE_SANTA) return SANTA;
        if (totalBenefit >= EVENT_BADGE_PRICE_TREE) return TREE;
        if (totalBenefit >= EVENT_BADGE_PRICE_STARS) return STARS;
        return EMPTY;
    }

    public String getEventBadge() {
        return eventBadge;
    }
}
