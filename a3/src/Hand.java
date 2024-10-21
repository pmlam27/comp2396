import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public abstract class Hand extends CardList {

    private final CardGamePlayer player;
    protected Card topCard;

    public Hand(CardGamePlayer player, CardList cards) {
        this.player = player;
        for(int i=0; i<cards.size(); i++) {
            this.addCard(cards.getCard(i));
        }
    }

    public CardGamePlayer getPlayer() {
        return player;
    }

    public Card getTopCard() {
        return topCard;
    }

    /**
     * by default compare rank first, and then suit
     * @param hand
     * @return
     */
    public boolean beats(Hand hand) {
        if(this.size() == hand.size()) {
            if (this.getTopCard().getRank() > hand.getTopCard().getRank()) {
                return true;
            } else if (this.getTopCard().getRank() == hand.getTopCard().getRank()) {
                return this.getTopCard().getSuit() > hand.getTopCard().getSuit();
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public abstract boolean isValid();
    public abstract String getType();

    protected ArrayList<CardList> groupCardsWithSameRank() {
        ArrayList<CardList> returnArray = new ArrayList<CardList>();
        HashSet<Integer> setOfRank = new HashSet<>();
        for(int i=0; i<this.size(); i++) {
            setOfRank.add(this.getCard(i).getRank());
        }
        setOfRank.forEach(currentRank -> {
            CardList group = new CardList();
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
