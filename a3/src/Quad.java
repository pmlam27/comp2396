import java.util.ArrayList;
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
            return CardOrder.bigTwoCompareWithRankFirst(this.getTopCard(), hand.getTopCard()) == 1;
        } else {
            return false;
        }
    }

    @Override
    public boolean isValid() {
        if(this.size() != 5) {
            return false;
        }

        ArrayList<CardGroup> listOfGroups = this.groupCardsWithSameRank();
        if(listOfGroups.size() != 2) {
            return false;
        }
        CardGroup groupWith4Card = null;
        CardGroup groupWith1Card = null;

        for(CardGroup group : listOfGroups) {
            if(group.size() == 4) {
                groupWith4Card = group;
            }
            if(group.size() == 1) {
                groupWith1Card = group;
            }
        }

        if(groupWith4Card != null && groupWith1Card != null) {
            topCard = groupWith4Card.highestRank();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getType() {
        return Hand.QUAD;
    }
}
