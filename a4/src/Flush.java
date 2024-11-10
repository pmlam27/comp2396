import java.util.Objects;

/**
 * models the flush hand type
 */
public class Flush extends Hand {
    /**
     * constructs flush hand
     * @param player the player
     * @param cards the cards
     */
    public Flush(CardGamePlayer player, CardList cards) {
        super(player, cards);
    }

    /**
     * check if this hand beats the given hand
     * @param hand the hand to compare
     * @return return whether it can be beaten
     */
    @Override
    public boolean beats(Hand hand) {
        if(Objects.equals(hand.getType(), STRAIGHT)) {
            return true;
        } else if(Objects.equals(hand.getType(), FLUSH)) {
            return BigTwo.compareWithSuitFirst(this.getTopCard(), hand.getTopCard()) == 1;
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
            this.allHaveSameSuit() &&
            !this.allHaveConsecutiveRank()
        ) {
            this.topCard = this.highestRank();
            return true;
        } else {
            return false;
        }
    }

    /**
     * get the string name of card
     * @return the name of type
     */
    @Override
    public String getType() {
        return Hand.FLUSH;
    }
}
