public class Triple extends Hand {
    public Triple(CardGamePlayer player, CardList cards) {
        super(player, cards);
    }

    @Override
    public boolean beats(Hand hand) {
        return super.beats(hand);
    }

    @Override
    public boolean isValid() {
        if(this.size() == 3 && this.allHaveSameRank()) {
            this.topCard = this.highestSuit();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getType() {
        return "Triple";
    }
}
