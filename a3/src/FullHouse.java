import java.util.ArrayList;

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
        if(this.size() != 5) {
            return false;
        }

        ArrayList<CardList> listOfGroup = this.groupCardsWithSameRank();
        if(listOfGroup.size() != 2) {
            return false;
        }
        CardList groupWith3Card = null;
        CardList groupWith2Card = null;

        for(CardList group : listOfGroup) {
            if(group.size() == 3) {
                groupWith3Card = group;
            }
            if(group.size() == 2) {
                groupWith2Card = group;
            }
        }

        if(groupWith3Card != null && groupWith2Card != null) {
            // TODO: assign top card
            // TODO: return true or false
        }
    }

    @Override
    public String getType() {
        return "FullHouse";
    }
}
