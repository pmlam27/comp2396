import java.util.Objects;
/**
 * models the straightFlush hand type
 */
public class StraightFlush extends Hand {
    /**
     * constructs straightFlush hand
     * @param player the player
     * @param cards the cards
     */
    public StraightFlush(CardGamePlayer player, CardList cards) {
        super(player, cards);
    }

    /**
     * check if this hand beats the given hand
     * @param hand the hand to compare
     * @return return whether it can be beaten
     */
    @Override
    public boolean beats(Hand hand) {
        if( Objects.equals(hand.getType(), STRAIGHT) ||
            Objects.equals(hand.getType(), FLUSH) ||
            Objects.equals(hand.getType(), FULL_HOUSE) ||
            Objects.equals(hand.getType(), QUAD)
        ) {
            return true;
        } else if(Objects.equals(hand.getType(), STRAIGHT_FLUSH)) {
            return BigTwo.compareWithRankFirst(this.getTopCard(), hand.getTopCard()) == 1;
        } else {
            return false;
        }
    }

    /**
     * check if the hand is valid
     * @return whether the hand is valid
     */
    @Override
    public boolean isValid() {
        if( this.size() == 5 &&
            this.allHaveConsecutiveRank() &&
            this.allHaveSameSuit()
        ) {
            topCard = this.highestRank();
            return true;
        } else {
            return false;
        }
    }

    /**
     * check if the hand is valid
     * @return whether the hand is valid
     */
    @Override
    public String getType() {
        return Hand.STRAIGHT_FLUSH;
    }
}
