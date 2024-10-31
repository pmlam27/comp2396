public class BigTwoCard extends Card {
    public BigTwoCard(int suit, int rank) {
        super(suit, rank);
    }

    @Override
    public int compareTo(Card card) {
        return CardOrder.bigTwoCompareWithRankFirst(this, card);
    }
}
