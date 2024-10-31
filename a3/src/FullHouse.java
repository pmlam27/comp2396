import java.util.ArrayList;
import java.util.Objects;

public class FullHouse extends Hand {
    public FullHouse(CardGamePlayer player, CardList cards) {
        super(player, cards);
    }

    @Override
    public boolean beats(Hand hand) {
        if( Objects.equals(hand.getType(), STRAIGHT) ||
            Objects.equals(hand.getType(), FLUSH)
        ) {
            return true;
        } else if (Objects.equals(hand.getType(), FULL_HOUSE)) {
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
        CardGroup groupWith3Card = null;
        CardGroup groupWith2Card = null;

        for(CardGroup group : listOfGroups) {
            if(group.size() == 3) {
                groupWith3Card = group;
            }
            if(group.size() == 2) {
                groupWith2Card = group;
            }
        }

        if(groupWith3Card != null && groupWith2Card != null) {
            topCard = groupWith3Card.highestRank();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getType() {
        return Hand.FULL_HOUSE;
    }
}
