import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * wrapper around CardList that includes additional utilities
 */
public class CardGroup extends CardList {
    protected ArrayList<CardGroup> groupCardsWithSameRank() {
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

    protected boolean allHaveConsecutiveRank() {
        ArrayList<CardGroup> cardGroups = this.groupCardsWithSameRank();
        if(cardGroups.size() != 5) {
            return false;
        }

        ArrayList<Integer> bigTwoRankOrderList = new ArrayList<>();
        for(int i=0; i<5; i++) {
            Card distinctCard = cardGroups.get(i).getCard(0);
            bigTwoRankOrderList.add(CardOrder.fromNormalToBigTwoOrder(distinctCard.getRank()));
        }


        bigTwoRankOrderList.sort(null);

        System.out.println(bigTwoRankOrderList);

        int listRange = bigTwoRankOrderList.getLast() - bigTwoRankOrderList.getFirst();
        System.out.println(listRange);
        return listRange == 4;
    }

    protected boolean allHaveSameRank() {
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

    protected boolean allHaveSameSuit() {
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

    protected Card highestRank() {
        Card highestCard = this.getCard(0);
        for(int i=0; i<this.size(); i++) {
            Card currentCard = this.getCard(i);
            if(CardOrder.bigTwoCompareWithRankFirst(currentCard, highestCard) == 1) {
                highestCard = currentCard;
            }
        }
        return highestCard;
    }

    protected Card highestSuit() {
        Card highestCard = this.getCard(0);
        for(int i=0; i<this.size(); i++) {
            Card currentCard = this.getCard(i);
            if(CardOrder.bigTwoCompareWithSuitFirst(currentCard, highestCard) == 1) {
                highestCard = currentCard;
            }
        }
        return highestCard;
    }
}
