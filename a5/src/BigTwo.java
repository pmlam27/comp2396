import java.util.ArrayList;

/**
 * this class models a Big Two card game
 */
public class BigTwo implements CardGame {

    private int numOfPlayers;
    private Deck deck;
    private ArrayList<CardGamePlayer> playerList;
    private ArrayList<Hand> handsOnTable;
    private int currentPlayerIdx;
    private BigTwoGUI ui;

    /**
     * this constructor creates the players and the UI object
     */
    public BigTwo() {
        numOfPlayers = 4;
        playerList = new ArrayList<>();
        playerList.add(new CardGamePlayer());
        playerList.add(new CardGamePlayer());
        playerList.add(new CardGamePlayer());
        playerList.add(new CardGamePlayer());

        handsOnTable = new ArrayList<>();

        ui = new BigTwoGUI(this);
    }

    /**
     * setup and starts the big two card game
     * @param args not used in this method
     */
    public static void main(String[] args) {
        BigTwo bigTwoGame = new BigTwo();
        BigTwoDeck deck = new BigTwoDeck();
        deck.initialize();
        deck.shuffle();
        bigTwoGame.start(deck);
    }

    /**
     * check if a given CardList is valid.
     * if it is valid, the composed hand is returned to the player.
     * if it is not valid, null is returned.
     * @param player the player
     * @param cards the list of card to compose
     * @return the composed hand or null
     */
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

    /**
     * compare the rank of cards in bigTwo order
     * @param cardA first card
     * @param cardB second card
     * @return 1 if greater, 0 if same, -1 if lesser
     */
    public static int compareWithRankFirst(Card cardA, Card cardB) {
        int ABigTwoRank = fromNormalToBigTwoOrder(cardA.getRank());
        int BBigTwoRank = fromNormalToBigTwoOrder(cardB.getRank());

        if (ABigTwoRank > BBigTwoRank) {
            return 1;
        } else if (ABigTwoRank < BBigTwoRank) {
            return -1;
        } else if (cardA.getSuit() > cardB.getSuit()) {
            return 1;
        } else if (cardA.getSuit() < cardB.getSuit()) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * compare the suit of cards in the order of bigTwo
     * @param cardA first card
     * @param cardB second card
     * @return 1 if greater, 0 if same, -1 if lesser
     */
    public static int compareWithSuitFirst(Card cardA, Card cardB) {
        int ABigTwoRank = fromNormalToBigTwoOrder(cardA.getRank());
        int BBigTwoRank = fromNormalToBigTwoOrder(cardB.getRank());

        if (cardA.getSuit() > cardB.getSuit()) {
            return 1;
        } else if (cardA.getSuit() < cardB.getSuit()) {
            return -1;
        } else if (ABigTwoRank > BBigTwoRank) {
            return 1;
        } else if (ABigTwoRank < BBigTwoRank) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * converts order from normal order to big two order
     * @param normalOrder the number of normal order
     * @return the corresponding big two order
     */
    public static int fromNormalToBigTwoOrder(int normalOrder) {
        /*
        lowest  0   1   2   3   4   5   6   7   8   9   10  11  12  highest
        normal: A   2   3   4   5   6   7   8   9   10  J   Q   K
        big 2:  3   4   5   6   7   8   9   10  J   Q   K   A   2

        normal to big2 mapping:
        {11, 12, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
         */

        int[] mapping = {11, 12, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        return mapping[normalOrder];
    }

    /**
     * get number of players
     * @return number of players
     */
    @Override
    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    /**
     * get the deck
     * @return return BigTwoDeck
     */
    @Override
    public Deck getDeck() {
        return deck;
    }

    /**
     * get the list of player
     * @return list of player
     */
    @Override
    public ArrayList<CardGamePlayer> getPlayerList() {
        return playerList;
    }

    /**
     * get the hands on the table
     * @return hands on the table
     */
    @Override
    public ArrayList<Hand> getHandsOnTable() {
        return handsOnTable;
    }

    /**
     * get the id of current player
     * @return
     */
    @Override
    public int getCurrentPlayerIdx() {
        return currentPlayerIdx;
    }

    /**
     * start the game by clearing everything, and then prompt the active player
     * @param deck the deck of (shuffled) cards to be used in this game
     */
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

    /**
     * simply calls check moves
     * @param playerIdx the index of the player who makes the move
     * @param cardIdx   the list of the indices of the cards selected by the player
     */
    @Override
    public void makeMove(int playerIdx, int[] cardIdx) {
        checkMove(playerIdx, cardIdx);
    }

    /**
     * check if the move of the player is valid
     * if it is valid, then make the move and check if the game should continue
     * @param playerIdx the index of the player who makes the move
     * @param cardIdx   the list of the indices of the cards selected by the player
     */
    @Override
    public void checkMove(int playerIdx, int[] cardIdx) {
        CardGamePlayer currentPlayer = playerList.get(playerIdx);
        Hand lastHand;
        if(handsOnTable.isEmpty()) {
            lastHand = null;
        } else {
            lastHand = handsOnTable.get(handsOnTable.size() - 1);
        }
        boolean playerCanPass = true;

        if(lastHand != null && lastHand.getPlayer() == currentPlayer) {
            playerCanPass = false;
        }

        if(cardIdx == null || cardIdx.length == 0) {
            if(playerCanPass) {
                ui.printMsg("{Pass}\n\n");
                ui.setActivePlayer((currentPlayerIdx + 1) % 4);
                currentPlayerIdx = (currentPlayerIdx + 1) % 4;
                ui.repaint();
                ui.promptActivePlayer();
                return;
            } else {
                ui.printMsg("Not a legal move!!!\n");
                ui.promptActivePlayer();
                return;
            }
        }

        // check if player actually have the cards
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

        // check if the hand is a valid hand
        Hand hand = composeHand(currentPlayer, cardsToCompose);
        if (hand == null) {
            ui.printMsg("Not a legal move!!!\n");
            ui.promptActivePlayer();
            return;
        }

        // check if the hand beats the last hand
        // note: this is not checked if the player cannot pass or the last round was passed
        if(playerCanPass && lastHand != null && !hand.beats(lastHand)){
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

        ui.printMsg(msg.append("\n\n").toString());

        if(!endOfGame()) {
            ui.setActivePlayer((currentPlayerIdx + 1) % 4);
            currentPlayerIdx = (currentPlayerIdx + 1) % 4;
            ui.repaint();
            ui.promptActivePlayer();
        } else {
            ui.setActivePlayer(-1);
            ui.repaint();
            ui.printMsg("\nGame ends\n");
            for(CardGamePlayer player : playerList) {
                if(player.getCardsInHand().isEmpty()) {
                    ui.printMsg(player.getName() + " wins the game.\n");
                } else {
                    ui.printMsg(
                        player.getName() +
                        " has " +
                        player.getCardsInHand().size() +
                        " cards in hand.\n"
                    );
                }
            }
        }
    }

    /**
     * check if the game has ended by reaching win condition
     * @return whether the game has ended or not
     */
    @Override
    public boolean endOfGame() {
        boolean anyoneHasNoCard = false;
        for(CardGamePlayer player : playerList) {
            if(player.getCardsInHand().isEmpty()) {
                anyoneHasNoCard = true;
            }
        }
        return anyoneHasNoCard;
    }


}
