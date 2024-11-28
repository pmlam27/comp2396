/**
 * models a card used in big two card game
 * changes the compareTo method to reflect big two order
 */
public class BigTwoCard extends Card {
    /**
     * constructs the big two card with the given suit and rank
     * @param suit the suit of the card
     * @param rank the rank of the card
     */
    public BigTwoCard(int suit, int rank) {
        super(suit, rank);
    }

    /**
     * compares this card to another card using bigTwo order
     * @param card the object to be compared.
     * @return -1 if this is larger, 0 if equal, 1 if smaller
     */
    @Override
    public int compareTo(Card card) {
        return BigTwo.compareWithRankFirst(this, card);
    }
}
