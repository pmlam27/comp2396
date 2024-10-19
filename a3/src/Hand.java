public abstract class Hand extends CardList {

    private CardGamePlayer player;

    public Hand(CardGamePlayer player, CardList cards) {
        // TODO: stuff
    }

    public CardGamePlayer getPlayer() {
        // TODO: stuff
        return new CardGamePlayer();
    }

    public Card getTopCard() {
        // TODO: stuff
        return new Card(1, 1);
    }

    public boolean beats(Hand hand) {
        // TODO: stuff
        return false;
    }

    public abstract boolean isValid();
    public abstract String getType();
}
