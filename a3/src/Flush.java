public class Flush extends Hand {

    public Flush(CardGamePlayer player, CardList cards) {
        super(player, cards);
    }

    /**
     * need to override since it compare suit first, and then rank
     * @param hand
     * @return
     */
    @Override
    public boolean beats(Hand hand) {
        boolean sizeIsSame = this.size() == hand.size();

        // placeholder
        return false;
    }

    @Override
    public boolean isValid() {
        if(this.size() == 5 && this.allHaveSameSuit()) {
            this.topCard = this.highestRank();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getType() {
        return "Flush";
    }
}
