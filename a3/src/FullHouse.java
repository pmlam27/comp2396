public class FullHouse extends Hand {
    public FullHouse(CardGamePlayer player, CardList cards) {
        super(player, cards);
    }

    @Override
    public boolean beats(Hand hand) {
        return super.beats(hand);
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public String getType() {
        return "FullHouse";
    }
}
