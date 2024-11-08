/**
 * models a hand of cards
 */
public abstract class Hand extends CardGroup {
    // use constants to store string representation of types to ensure consistency
    /**
     * string name for single
     */
    protected static final String SINGLE = "Single";
    /**
     * string name for pair
     */
    protected static final String PAIR = "Pair";
    /**
     * string name for triple
     */
    protected static final String TRIPLE = "Triple";
    /**
     * string name for straight
     */
    protected static final String STRAIGHT = "Straight";
    /**
     * string name for flush
     */
    protected static final String FLUSH = "Flush";
    /**
     * string name for full house
     */
    protected static final String FULL_HOUSE = "FullHouse";
    /**
     * string name for quad
     */
    protected static final String QUAD = "Quad";
    /**
     * string name for straight flush
     */
    protected static final String STRAIGHT_FLUSH = "StraightFlush";

    /**
     * the player who plays this hand
     */
    private final CardGamePlayer player;
    /**
     * stores the top card of the hand for comparisons
     */
    protected Card topCard;

    /**
     * builds a hand with the player and list of cards.
     * @param player the player
     * @param cards the list of cards
     */
    public Hand(CardGamePlayer player, CardList cards) {
        this.player = player;
        for(int i=0; i<cards.size(); i++) {
            this.addCard(cards.getCard(i));
        }
    }

    /**
     * get the player of this hand
     * @return
     */
    public CardGamePlayer getPlayer() {
        return player;
    }

    /**
     * get the top card of this hand
     * @return the top card
     */
    public Card getTopCard() {
        return topCard;
    }

    /**
     * by default compare rank first, and then suit
     * @param hand the hand to compare
     * @return whether this hand can beat the given hand
     */
    public boolean beats(Hand hand) {
        if(this.size() == hand.size()) {
            return BigTwo.compareWithRankFirst(this.getTopCard(), hand.getTopCard()) == 1;
        } else {
            return false;
        }
    }

    /**
     * check if the composed hand is valid
     * @return whether the hand is valid
     */
    public abstract boolean isValid();

    /**
     * get the string name of this hand
     * @return the string name of this hand
     */
    public abstract String getType();
}
