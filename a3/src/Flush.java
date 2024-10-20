public class Flush extends Hand {

    public Flush(CardGamePlayer player, CardList cards) {
        super(player, cards);
    }

    @Override
    public boolean beats(Hand hand) {
        boolean sizeIsSame = this.size() == hand.size();

        // placeholder
        return false;
    }

    @Override
    public boolean isValid() {
        boolean sizeIsValid = this.size() == 5;
        boolean contentIsValid = true;

        int suitOfFirstCard = this.getCard(0).getSuit();
        for (int i=1; i<5; i++) {
            int suitOfCurrentCard = this.getCard(i).getSuit();
            if(suitOfCurrentCard != suitOfFirstCard) {
                contentIsValid = false;
            }
        }
        return sizeIsValid && contentIsValid;
    }

    @Override
    public String getType() {
        return "Flush";
    }
}
