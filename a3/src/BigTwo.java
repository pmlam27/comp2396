import java.util.ArrayList;

public class BigTwo implements CardGame {

    private int numOfPlayers;
    private Deck deck;
    private ArrayList<CardGamePlayer> playerList;
    private ArrayList<Hand> handsOnTable;
    private int currentPlayerIdx;
    private BigTwoUI ui;

    public BigTwo() {
        numOfPlayers = 4;
        playerList = new ArrayList<>();
        playerList.add(new CardGamePlayer());
        playerList.add(new CardGamePlayer());
        playerList.add(new CardGamePlayer());
        playerList.add(new CardGamePlayer());

        handsOnTable = new ArrayList<>();

        ui = new BigTwoUI(this);
    }

    public static void main(String[] args) {
        BigTwo bigTwoGame = new BigTwo();
        BigTwoDeck deck = new BigTwoDeck();
        deck.initialize();
        deck.shuffle();
        bigTwoGame.start(deck);
    }

    public static Hand composeHand(CardGamePlayer player, CardList cards) {
        Hand[] possibleHands = {
                new Single(player, cards), new Pair(player, cards), new Triple(player, cards),
                new Straight(player,cards), new Flush(player, cards), new FullHouse(player, cards),
                new Quad(player,cards), new StraightFlush(player, cards),
        };

        for (Hand possibleHand : possibleHands) {
            if(possibleHand.isValid()) {
                return possibleHand;
            }
        }
        return null;
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
        handsOnTable.clear();

        playerList.forEach(CardGamePlayer::removeAllCards);

        int playerIdWithThreeDiamond = -1;

        for (int i = 0; i < 52; i++) {
            int playerId = Math.floorDiv(i, 13);
            CardGamePlayer currentPlayer = playerList.get(playerId);
            Card cardToAdd = deck.getCard(i);

            if (cardToAdd.getRank() == 2 && cardToAdd.getSuit() == 0) {
                playerIdWithThreeDiamond = playerId;
            }
            currentPlayer.addCard(deck.getCard(i));
        }

        currentPlayerIdx = playerIdWithThreeDiamond;
        ui.setActivePlayer(playerIdWithThreeDiamond);

        ui.repaint();
        ui.promptActivePlayer();
    }

    @Override
    public void makeMove(int playerIdx, int[] cardIdx) {
        checkMove(playerIdx, cardIdx);
    }

    @Override
    public void checkMove(int playerIdx, int[] cardIdx) {
        CardGamePlayer currentPlayer = playerList.get(playerIdx);

        if(cardIdx.length == 0) {
            ui.printMsg("{Pass}");
        }

        CardList cardsHeld = currentPlayer.getCardsInHand();
        CardList cardsToCompose = new CardList();
        for(int cardId : cardIdx) {
            boolean playerHaveSpecifiedCard = false;
            for(int i=0; i<cardsHeld.size(); i++) {
                Card currentCard = cardsHeld.getCard(i);
                if(cardId == i) {
                    playerHaveSpecifiedCard = true;
                    cardsToCompose.addCard(currentCard);
                }
            }

            if(!playerHaveSpecifiedCard) {
                ui.printMsg("Not a legal move!!!\n");
                ui.promptActivePlayer();
                return;
            }
        }

        Hand hand = composeHand(currentPlayer, cardsToCompose);
        if (hand == null) {
            ui.printMsg("Not a legal move!!!\n");
            ui.promptActivePlayer();
            return;
        }

        handsOnTable.add(hand);

        currentPlayer.removeCards(hand);

        StringBuilder msg = new StringBuilder("{" + hand.getType() + "}");
        for(int i=0; i < hand.size(); i++) {
            msg .append("[")
                    .append(hand.getCard(i))
                    .append("]");
        }

        ui.printMsg(msg.append("\n").toString());

        if(!endOfGame()) {
            ui.setActivePlayer((currentPlayerIdx + 1) % 4);
            currentPlayerIdx = (currentPlayerIdx + 1) % 4;
            ui.repaint();
            ui.promptActivePlayer();
        }
    }

    @Override
    public boolean endOfGame() {
        // TODO: stuff
        return false;
    }
}
