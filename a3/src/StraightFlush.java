import java.util.Objects;

public class StraightFlush extends Hand {
    public StraightFlush(CardGamePlayer player, CardList cards) {
        super(player, cards);
    }

    @Override
    public boolean beats(Hand hand) {
        if( Objects.equals(hand.getType(), STRAIGHT) ||
            Objects.equals(hand.getType(), FLUSH) ||
            Objects.equals(hand.getType(), FULL_HOUSE) ||
            Objects.equals(hand.getType(), QUAD)
        ) {
            return true;
        } else if(Objects.equals(hand.getType(), STRAIGHT_FLUSH)) {
            return CardOrder.bigTwoCompareWithRankFirst(this.getTopCard(), hand.getTopCard()) == 1;
        } else {
            return false;
        }
    }

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

    @Override
    public String getType() {
        return Hand.STRAIGHT_FLUSH;
    }
}
