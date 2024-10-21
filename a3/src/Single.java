public class Single extends Hand {
    public Single(CardGamePlayer player, CardList cards) {
        super(player, cards);
    }

    @Override
    public boolean beats(Hand hand) {
        return super.beats(hand);
    }

    @Override
    public boolean isValid() {
        if(this.size() == 1) {
            this.topCard = this.getCard(0);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getType() {
        return "Single";
    }
}
