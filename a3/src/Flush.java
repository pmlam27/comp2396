public class Flush extends Hand {

    public Flush(CardGamePlayer player, CardList cards) {
        super(player, cards);
    }

    @Override
    public boolean isValid() {
        // TODO: stuff
        return false;
    }

    @Override
    public String getType() {
        return "Flush";
    }
}
