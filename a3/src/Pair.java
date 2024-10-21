public class Pair extends Hand {
    public Pair(CardGamePlayer player, CardList cards) {
        super(player, cards);
    }

    @Override
    public boolean beats(Hand hand) {
        return super.beats(hand);
    }

    @Override
    public boolean isValid() {
        if(this.size() == 2 && this.allHaveSameRank()) {
            this.topCard = this.highestSuit();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getType() {
        return "Pair";
    }
}
