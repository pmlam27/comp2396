/**
 * models the single hand type
 */
public class Single extends Hand {

    /**
     * constructs single hand
     * @param player the player
     * @param cards the cards
     */
    public Single(CardGamePlayer player, CardList cards) {
        super(player, cards);
    }

    /**
     * check if the hand is valid
     * @return whether the hand is valid
     */
    @Override
    public boolean isValid() {
        if(this.size() == 1) {
            this.topCard = this.getCard(0);
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
        return Hand.SINGLE;
    }
}
