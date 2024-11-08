import java.util.ArrayList;
import java.util.HashSet;

/**
 * this class inherits from cardList,
 * and includes additional utilities that makes comparison easier
 */
public class CardGroup extends CardList {
    /**
     * group together the cards with the same rank
     * @return a list of CardGroups
     */
    public ArrayList<CardGroup> groupCardsWithSameRank() {
        ArrayList<CardGroup> returnArray = new ArrayList<CardGroup>();
        HashSet<Integer> setOfRank = new HashSet<>();
        for(int i=0; i<this.size(); i++) {
            setOfRank.add(this.getCard(i).getRank());
        }
        setOfRank.forEach(currentRank -> {
            CardGroup group = new CardGroup();
            for(int i=0; i<this.size(); i++) {
                if(this.getCard(i).getRank() == currentRank) {
                    group.addCard(this.getCard(i));
                }
            }
            returnArray.add(group);
        });
        return returnArray;
    }

    /**
     * check if all cards in the list have consecutive rank
     * @return whether all cards are consecutive
     */
    public boolean allHaveConsecutiveRank() {
        ArrayList<CardGroup> cardGroups = this.groupCardsWithSameRank();
        if(cardGroups.size() != 5) {
            return false;
        }

        ArrayList<Integer> bigTwoRankOrderList = new ArrayList<>();
        for(int i=0; i<5; i++) {
            Card distinctCard = cardGroups.get(i).getCard(0);
            bigTwoRankOrderList.add(BigTwo.fromNormalToBigTwoOrder(distinctCard.getRank()));
        }

        bigTwoRankOrderList.sort(null);

        System.out.println(bigTwoRankOrderList);
        int listRange = bigTwoRankOrderList.get(bigTwoRankOrderList.size()-1) - bigTwoRankOrderList.get(0);
        System.out.println(listRange);
        return listRange == 4;
    }

    /**
     * check if all cards have the same rank.
     * @return whether all cards have the same rank.
     */
    public boolean allHaveSameRank() {
        boolean allIsSame = true;
        int firstCardRank = this.getCard(0).getRank();
        for(int i=0; i<this.size(); i++) {
            int currentCardRank = this.getCard(i).getRank();
            if(currentCardRank != firstCardRank) {
                allIsSame = false;
            }
        }
        return allIsSame;
    }

    /**
     * check if all cards have the same suit.
     * @return whether all cards have the same suit.
     */
    public boolean allHaveSameSuit() {
        boolean allIsSame = true;
        int firstCardSuit = this.getCard(0).getSuit();
        for(int i=0; i<this.size(); i++) {
            int currentCardSuit = this.getCard(i).getSuit();
            if(currentCardSuit != firstCardSuit) {
                allIsSame = false;
            }
        }
        return allIsSame;
    }

    /**
     * find the card with the highest rank
     * @return the card with the highest rank
     */
    public Card highestRank() {
        Card highestCard = this.getCard(0);
        for(int i=0; i<this.size(); i++) {
            Card currentCard = this.getCard(i);
            if(BigTwo.compareWithRankFirst(currentCard, highestCard) == 1) {
                highestCard = currentCard;
            }
        }
        return highestCard;
    }

    /**
     * find the card with the highest suit
     * @return the card with the highest suit
     */
    public Card highestSuit() {
        Card highestCard = this.getCard(0);
        for(int i=0; i<this.size(); i++) {
            Card currentCard = this.getCard(i);
            if(BigTwo.compareWithSuitFirst(currentCard, highestCard) == 1) {
                highestCard = currentCard;
            }
        }
        return highestCard;
    }
}
