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

    protected boolean containRank(int rank) {
        boolean rankMatch = false;
        for(int i=0; i<this.size(); i++) {
            if (this.getCard(i).getRank() == rank) {
                rankMatch = true;
            }
        }
        return rankMatch;
    }

    protected boolean allHaveConsecutiveRank() {
        boolean atLeast1Consecutive = false;
        for(int i=0; i<this.size(); i++) {
            int currentCardRank = this.getCard(i).getRank();
            if (    this.containRank(currentCardRank+1) &&
                    this.containRank(currentCardRank+2) &&
                    this.containRank(currentCardRank+3) &&
                    this.containRank(currentCardRank+4)
            ) {
                atLeast1Consecutive = true;
            }
        }
        return atLeast1Consecutive;
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
            if(currentCard.getRank() > highestCard.getRank()) {
                highestCard = currentCard;
            }
        }
        return highestCard;
    }

    protected Card highestSuit() {
        Card highestCard = this.getCard(0);
        for(int i=0; i<this.size(); i++) {
            Card currentCard = this.getCard(i);
            if(currentCard.getSuit() > highestCard.getSuit()) {
                highestCard = currentCard;
            }
        }
        return highestCard;
    }
}
