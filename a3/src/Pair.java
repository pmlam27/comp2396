/**
 * models the pair hand type
 */
public class Pair extends Hand {
    /**
     * constructs pair hand
     * @param player the player
     * @param cards the cards
     */
    public Pair(CardGamePlayer player, CardList cards) {
        super(player, cards);
    }

    /**
     * check if the hand is valid
     * @return whether the hand is valid
     */
    @Override
    public boolean isValid() {
        if(this.size() == 2 && this.allHaveSameRank()) {
            this.topCard = this.highestSuit();
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
        return Hand.PAIR;
    }
}
