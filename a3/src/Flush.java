import java.util.Objects;

public class Flush extends Hand {

    public Flush(CardGamePlayer player, CardList cards) {
        super(player, cards);
    }

    /**
     * need to override since it compare suit first, and then rank
     * @param hand
     * @return
     */
    @Override
    public boolean beats(Hand hand) {
        if(Objects.equals(hand.getType(), STRAIGHT)) {
            return true;
        } else if(Objects.equals(hand.getType(), FLUSH)) {
            return CardOrder.bigTwoCompareWithSuitFirst(this.getTopCard(), hand.getTopCard()) == 1;
        } else {
            return false;
        }
    }

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

    @Override
    public String getType() {
        return Hand.FLUSH;
    }
}
