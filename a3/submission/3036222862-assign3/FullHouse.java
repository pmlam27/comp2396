import java.util.ArrayList;
import java.util.Objects;

/**
 * models the fullHouse hand type
 */
public class FullHouse extends Hand {
    /**
     * constructs fullHouse hand
     * @param player the player
     * @param cards the cards
     */
    public FullHouse(CardGamePlayer player, CardList cards) {
        super(player, cards);
    }

    /**
     * check if this hand beats the given hand
     * @param hand the hand to compare
     * @return return whether it can be beaten
     */
    @Override
    public boolean beats(Hand hand) {
        if( Objects.equals(hand.getType(), STRAIGHT) ||
            Objects.equals(hand.getType(), FLUSH)
        ) {
            return true;
        } else if (Objects.equals(hand.getType(), FULL_HOUSE)) {
            return BigTwo.compareWithRankFirst(this.getTopCard(), hand.getTopCard()) == 1;
        } else {
            return false;
        }
    }

    /**
     * check if the hand is valid
     * @return whether the hand is valid
     */
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

    /**
     * get the string name of card
     * @return the name of type
     */
    @Override
    public String getType() {
        return Hand.FULL_HOUSE;
    }
}
