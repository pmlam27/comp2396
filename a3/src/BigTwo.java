import java.util.ArrayList;

public class BigTwo implements CardGame {

    private int numOfPlayers;
    private Deck deck;
    private ArrayList<CardGamePlayer> playerList;
    private ArrayList<Hand> handsOnTable;
    private int currentPlayerIdx;
    // private BigTwoUI ui;

    public BigTwo() {
        numOfPlayers = 4;
        ArrayList<CardGamePlayer> players = new ArrayList<CardGamePlayer>();
        players.add(new CardGamePlayer());
        players.add(new CardGamePlayer());
        players.add(new CardGamePlayer());
        players.add(new CardGamePlayer());

        // TODO: create BigTwoUI
    }

    @Override
    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    @Override
    public Deck getDeck() {
        return deck;
    }

    @Override
    public ArrayList<CardGamePlayer> getPlayerList() {
        return playerList;
    }

    @Override
    public ArrayList<Hand> getHandsOnTable() {
        return handsOnTable;
    }

    @Override
    public int getCurrentPlayerIdx() {
        return currentPlayerIdx;
    }

    @Override
    public void start(Deck deck) {
        this.deck = deck;

        playerList.forEach(CardGamePlayer::removeAllCards);

        for (int i = 0; i < 52; i++) {
            int currentId = Math.floorDiv(i, 13);
        }

        // TODO: distribute cards

        // TODO: stuff
    }

    @Override
    public void makeMove(int playerIdx, int[] cardIdx) {
        // TODO: stuff
    }

    @Override
    public void checkMove(int playerIdx, int[] cardIdx) {
        // TODO: stuff
    }

    @Override
    public boolean endOfGame() {
        // TODO: stuff
        return false;
    }
}
