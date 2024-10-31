import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public abstract class Hand extends CardGroup {
    // use constants to store string representation of types to ensure consistency
    protected static final String SINGLE = "Single";
    protected static final String PAIR = "Pair";
    protected static final String TRIPLE = "Triple";
    protected static final String STRAIGHT = "Straight";
    protected static final String FLUSH = "Flush";
    protected static final String FULL_HOUSE = "FullHouse";
    protected static final String QUAD = "Quad";
    protected static final String STRAIGHT_FLUSH = "StraightFlush";

    private final CardGamePlayer player;
    protected Card topCard;

    public Hand(CardGamePlayer player, CardList cards) {
        this.player = player;
        for(int i=0; i<cards.size(); i++) {
            this.addCard(cards.getCard(i));
        }
    }

    public CardGamePlayer getPlayer() {
        return player;
    }

    public Card getTopCard() {
        return topCard;
    }

    /**
     * by default compare rank first, and then suit
     * @param hand
     * @return
     */
    public boolean beats(Hand hand) {
        if(this.size() == hand.size()) {
            return CardOrder.bigTwoCompareWithRankFirst(this.getTopCard(), hand.getTopCard()) == 1;
        } else {
            return false;
        }
    }

    public abstract boolean isValid();
    public abstract String getType();
}
