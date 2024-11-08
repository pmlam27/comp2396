import java.util.ArrayList;
import java.util.Objects;

/**
 * models the quad hand type
 */
public class Quad extends Hand {
    /**
     * constructs quad hand
     * @param player the player
     * @param cards the cards
     */
    public Quad(CardGamePlayer player, CardList cards) {
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
            Objects.equals(hand.getType(), FLUSH) ||
            Objects.equals(hand.getType(), FULL_HOUSE)
        ) {
            return true;
        } else if(Objects.equals(hand.getType(), QUAD)) {
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

    /**
     * get the string name of card
     * @return the name of type
     */
    @Override
    public String getType() {
        return Hand.QUAD;
    }
}
