import java.util.Objects;

public class Straight extends Hand {
    public Straight(CardGamePlayer player, CardList cards) {
        super(player, cards);
    }

    @Override
    public boolean beats(Hand hand) {
        if(Objects.equals(hand.getType(), Hand.STRAIGHT)) {
            return CardOrder.bigTwoCompareWithRankFirst(this.getTopCard(), hand.getTopCard()) == 1;
        } else {
            return false;
        }
    }

    @Override
    public boolean isValid() {
        if (    this.size() == 5 &&
                this.allHaveConsecutiveRank() &&
                !this.allHaveSameSuit()
        ) {
            topCard = this.highestRank();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getType() {
        return Hand.STRAIGHT;
    }
}
