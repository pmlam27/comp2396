public abstract class Hand extends CardList {

    private CardGamePlayer player;
    private CardList cards;

    public Hand(CardGamePlayer player, CardList cards) {
        this.player = player;
        this.cards = cards;
    }

    public CardGamePlayer getPlayer() {
        return player;
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
