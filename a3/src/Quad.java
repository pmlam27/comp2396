public class Quad extends Hand {
    public Quad(CardGamePlayer player, CardList cards) {
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
        return "Quad";
    }
}
