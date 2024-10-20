public class HandAttribute {
    private final Card topCard;
    private final HandCombination handCombination;

    public HandAttribute(Card topCard, HandCombination handCombination) {
        this.topCard = topCard;
        this.handCombination = handCombination;
    }

    public Card getTopCard() {
        return topCard;
    }

    public HandCombination getHandCombination() {
        return handCombination;
    }
}
