import java.util.Objects;

public class Quad extends Hand {
    public Quad(CardGamePlayer player, CardList cards) {
        super(player, cards);
    }

    @Override
    public boolean beats(Hand hand) {
        if( Objects.equals(hand.getType(), STRAIGHT) ||
            Objects.equals(hand.getType(), FLUSH) ||
            Objects.equals(hand.getType(), FULL_HOUSE)
        ) {
            return true;
        } else if(Objects.equals(hand.getType(), QUAD)) {
            return rankIsGreaterThan(this.getTopCard(), hand.getTopCard());
        } else {
            return false;
        }
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public String getType() {
        return Hand.QUAD;
    }
}
