package christmas.domain;

import static christmas.util.Const.*;

public class EventBadge {
    private final String eventBadge;

    public EventBadge(int totalBenefit) {
        this.eventBadge = calculateBadge(totalBenefit);
    }

    public static String calculateBadge(int totalBenefit) {
        if (totalBenefit >= 20000) return SANTA;
        if (totalBenefit >= 10000) return TREE;
        if (totalBenefit >= 5000) return STARS;
        return EMPTY;
    }
    public String getEventBadge() {
        return eventBadge;
    }
}
