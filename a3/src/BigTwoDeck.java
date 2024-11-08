/**
 * models a deck in big two game
 */
public class BigTwoDeck extends Deck {
    /**
     * initialize a deck of big two card
      */
    @Override
    public void initialize() {
        this.removeAllCards();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                addCard(new BigTwoCard(i, j));
            }
        }
    }
}
