import java.util.ArrayList;

public class BigTwo implements CardGame {

    private int numOfPlayers;
    private Deck deck;
    private ArrayList<CardGamePlayer> playerList;
    private ArrayList<Hand> handsOnTable;
    private int currentPlayerIdx;
    // private BigTwoUI ui;

    public BigTwo() {
        // TODO: constructor
    }

    @Override
    public int getNumOfPlayers() {
        // TODO: stuff
        return 0;
    }

    @Override
    public Deck getDeck() {
        // TODO: stuff
        return null;
    }

    @Override
    public ArrayList<CardGamePlayer> getPlayerList() {
        // TODO: stuff
        return null;
    }

    @Override
    public ArrayList<Hand> getHandsOnTable() {
        // TODO: stuff
        return null;
    }

    @Override
    public int getCurrentPlayerIdx() {
        // TODO: stuff
        return 0;
    }

    @Override
    public void start(Deck deck) {
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
