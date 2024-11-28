import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * the big two GUI
 */
public class BigTwoGUI implements CardGameUI {
    private static int frameWidth = 800;
    private static int frameHeight = 800;
    private static ArrayList<JLabel> cardPictures;
    private static CardList exampleList = new CardList();
    private JFrame frame;
    private static String gameStatusMessages = "";
    private static String userMessages = "";
    private BigTwo game;
    private ArrayList<CardGamePlayer> playerList; // the list of players
    private ArrayList<PlayerPanel> playerPanels = new ArrayList<>();
    private boolean panelsHaveInitialized = false;
    private HandPanel handPanel;
    private TopRightPanel topRightPanel;

    static {
        for(int i=0; i<100; i++) {
            gameStatusMessages = gameStatusMessages + "\n";
        }
        for(int i=0; i<12; i++) {
            exampleList.addCard(new Card(0, i));
        }
    }

    /**
     * construct the GUI
     * @param game BigTwo game
     */
    public BigTwoGUI(BigTwo game) {
        BigTwoPanel bigTwoPanel = new BigTwoPanel();
        // System.out.println(game);
        this.game = game;
        this.playerList = game.getPlayerList();

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setMinimumSize(new Dimension(300, 300));

        frame.add(bigTwoPanel);
        frame.setVisible(true);

        frame.repaint();
    }

    /**
     * the entire panel of BigTwo that contain other panels
     */
    public class BigTwoPanel extends JPanel {
        /**
         * constructs the big two panel
         */
        public BigTwoPanel() {
            setLayout(new GridBagLayout());

            /*
            leftPanel (x=0, y=0-1)
            topRightPanel (x=1, y=0)
            bottomRightPanel (x=1, y=1)
            bottomBar (x=0-1, y=2)
            */
            LeftPanel leftPanel = new LeftPanel();
            add(leftPanel, leftPanel.getGbc());

            topRightPanel = new TopRightPanel();

            add(topRightPanel, topRightPanel.getGbc());

            BottomRightPanel bottomRightPanel = new BottomRightPanel();
            add(bottomRightPanel, bottomRightPanel.getGbc());

            BottomBar bottomBar = new BottomBar();
            add(bottomBar, bottomBar.getGbc());
        }
    }

    /**
     * the panel that represent the bottom bar
     */
    public class BottomBar extends JPanel {
        /**
         * the gbc that bigTwoPanel will use
         * @return gbc
         */
        public GridBagConstraints getGbc() {
            GridBagConstraints gbc = new GridBagConstraints();
            // gbc to be used by BigTwoPanel
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridheight = 1;
            gbc.gridwidth = 2;
            gbc.weightx = 1;
            gbc.weighty = 0.1;
            return gbc;
        }

        /**
         * constructs the bottom bar panel
         */
        public BottomBar() {
            setLayout(new GridBagLayout());
            // setBorder(BorderFactory.createLineBorder(Color.darkGray));

            GridBagConstraints subGbc = new GridBagConstraints();
            // subGbc.fill = GridBagConstraints.BOTH;
            subGbc.weightx = 1;
            subGbc.weighty = 1;

            JButton playButton = new JButton();
            playButton.setText("Play");
            playButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for(PlayerPanel playerPanel : playerPanels) {
                            if (playerPanel.playerId == game.getCurrentPlayerIdx()) {
                                CardList cardsOnPlayer = game.getPlayerList().get(game.getCurrentPlayerIdx()).getCardsInHand();
                                CardList selectedCards = playerPanel.getSelectedCards();
                                if (selectedCards.isEmpty()) {
                                    return;
                                }
                                int[] cardId = new int[selectedCards.size()];
                                for(int i=0; i<selectedCards.size(); i++) {
                                    int idOfSelectedCard = -1;
                                    for(int j=0; j<cardsOnPlayer.size(); j++) {
                                        if (cardsOnPlayer.getCard(j) == selectedCards.getCard(i)) {
                                            idOfSelectedCard = j;
                                        }
                                    }
                                    cardId[i] = idOfSelectedCard;
                                }
                                game.makeMove(game.getCurrentPlayerIdx(), cardId);
                            }
                        }
                    }
                });
            subGbc.gridx = 0;
            add(playButton, subGbc);

            JButton passButton = new JButton();
            passButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for(PlayerPanel playerPanel : playerPanels) {
                                if (playerPanel.playerId == game.getCurrentPlayerIdx()) {
                                    game.makeMove(game.getCurrentPlayerIdx(), new int[] {});
                                }
                            }
                        }
                    });
            passButton.setText("Pass");
            subGbc.gridx = 1;
            add(passButton, subGbc);

            JLabel messagePrompt = new JLabel();
            messagePrompt.setText("Message: ");
            subGbc.gridx = 2;
            subGbc.anchor = GridBagConstraints.LINE_END;
            add(messagePrompt, subGbc);

            JTextField messageInput = new JTextField();
            messageInput.setPreferredSize(new Dimension(150, 30));
            subGbc.anchor = GridBagConstraints.CENTER;
            subGbc.gridx = 3;

            messageInput.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    userMessages += messageInput.getText() + "\n";
                    messageInput.setText("");
                    frame.repaint();
                }
            });

            add(messageInput, subGbc);
        }
    }

    /**
     * represent the top right panel
     */
    public class TopRightPanel extends JPanel {
        static JTextArea messageArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane();

        /**
         * the gbc that bigTwoPanel will use
         * @return gbc
         */
        public GridBagConstraints getGbc() {
            GridBagConstraints gbc = new GridBagConstraints();
            // gbc to be used by BigTwoPanel
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            gbc.weightx = 0.5;
            gbc.weighty = 0.5;
            return gbc;
        }

        /**
         * constructs the top right panel
         */
        public TopRightPanel() {
            setBorder(BorderFactory.createLineBorder(Color.darkGray));
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;
            gbc.weighty = 1;
            messageArea.setText(gameStatusMessages);
            messageArea.setEditable(false);
            scrollPane = new JScrollPane(messageArea);
            add(scrollPane, gbc);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            messageArea.setText(gameStatusMessages);
        }
    }

    /**
     * represents the bottom right panel
     */
    public class BottomRightPanel extends JPanel {

        static JTextArea messageArea = new JTextArea();
        /**
         * the gbc that bigTwoPanel will use
         * @return gbc
         */
        public GridBagConstraints getGbc() {
            GridBagConstraints gbc = new GridBagConstraints();
            // gbc to be used by BigTwoPanel
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            gbc.weightx = 0.5;
            gbc.weighty = 0.5;
            return gbc;
        }

        /**
         * constructs the bottom right panel
         */
        public BottomRightPanel() {
            setBorder(BorderFactory.createLineBorder(Color.darkGray));
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;
            gbc.weighty = 1;
            messageArea.setText(userMessages);
            messageArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(messageArea);
            add(scrollPane, gbc);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (Objects.equals(messageArea.getText(), userMessages)) {
                return;
            }
            messageArea.setText(userMessages);
        }
    }

    /**
     * represents the leftpanel that contain players and hand panel
     */
    public class LeftPanel extends JPanel {
        /**
         * the gbc that bigTwoPanel will use
         * @return gbc
         */
        public GridBagConstraints getGbc() {
            GridBagConstraints gbc = new GridBagConstraints();
            // gbc to be used by BigTwoPanel
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridheight = 2;
            gbc.gridwidth = 1;
            gbc.weightx = 0.5;
            gbc.weighty = 1;
            return gbc;
        }

        /**
         * the constructor for left panel
         */
        public LeftPanel() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.gridx = 0;

            playerPanels.add(new PlayerPanel(0));
            playerPanels.add(new PlayerPanel(1));
            playerPanels.add(new PlayerPanel(2));
            playerPanels.add(new PlayerPanel(3));

            for (int i=0; i<playerPanels.size(); i++) {
                gbc.gridy = i;
                playerPanels.get(i).setBorder(BorderFactory.createLineBorder(Color.darkGray));
                add(playerPanels.get(i), gbc);
            }

            handPanel = new HandPanel();
            gbc.gridy = 4;
            handPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
            add(handPanel, gbc);

        }
    }

    /**
     * represent the player panel
     */
    public class PlayerPanel extends JPanel {
        private int playerId;
        private CardList cardsInHand;
        private boolean panelInitialized = false;
        private LayeredCards layeredCards;
        private JLabel playerNameLabel;

        /**
         * constructs the player panel
         * @param playerId the id of player
         */
        public PlayerPanel(int playerId) {
            this.playerId = playerId;
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.gridx = 0;
            gbc.gridy = 1;
            String avatar;
            if (playerId == 0) {
                avatar = "\uD83D\uDE00";
            } else if (playerId == 1) {
                avatar = "\uD83D\uDE05";
            } else if (playerId == 2) {
                avatar = "\uD83E\uDD11";
            } else {
                avatar = "\uD83E\uDD7A";
            }

            JLabel playerAvatar = new JLabel(avatar);
            playerAvatar.setFont(new Font("Serif", Font.PLAIN, 40));
            add(playerAvatar, gbc);

            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 1;
            gbc.weighty = 0.5;
            gbc.gridx = 0;
            gbc.gridy = 0;
            playerNameLabel = new JLabel("");
            add(playerNameLabel, gbc);

            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridheight = 2;
            gbc.weightx = 4;
            gbc.weighty = 1;
            gbc.gridx = 1;
            gbc.gridy = 0;
            layeredCards = new LayeredCards(new CardList());
            add(layeredCards, gbc);
        }

        /**
         * get the cards selected by players
         * @return the cards selected by players
         */
        public CardList getSelectedCards() {
            return layeredCards.getCardsSelected();
        }

        /**
         * initialize the playerPanel during game start
         * @param currentPlayerId the id of current player
         */
        public void init(int currentPlayerId) {
            // int currentPlayerId = game.getCurrentPlayerIdx();
            cardsInHand = playerList.get(playerId).getCardsInHand();

            if (playerId == currentPlayerId) {
                layeredCards.setupClickable(cardsInHand);
                playerNameLabel.setText("You");
                return;
            }
            playerNameLabel.setText("Player " + playerId);
            layeredCards.setupHidden(cardsInHand.size());
        }

        /**
         * initialize the playerPanel
         */
        public void init() {
            int currentPlayerId = game.getCurrentPlayerIdx();
            cardsInHand = playerList.get(playerId).getCardsInHand();

            if (playerId == currentPlayerId) {
                layeredCards.setupClickable(cardsInHand);
                playerNameLabel.setText("You");
                return;
            }
            playerNameLabel.setText("Player " + playerId);
            layeredCards.setupHidden(cardsInHand.size());
        }
    }

    /**
     * represents the hand panel
     */
    public class HandPanel extends JPanel {
        private LayeredCards layeredCards;

        /**
         * constructs the handPanel
         */
        public HandPanel() {
            setPreferredSize(new Dimension(70, 70));
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0.3;
            gbc.weighty = 1;
            gbc.gridx = 0;
            gbc.gridy = 0;
            String avatar = "\uD83C\uDCCF";
            JLabel playerAvatar = new JLabel(avatar);
            playerAvatar.setFont(new Font("Serif", Font.PLAIN, 60));
            add(playerAvatar, gbc);

            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.gridx = 1;
            gbc.gridy = 0;
            layeredCards = new LayeredCards(new CardList());
            add(layeredCards, gbc);
        }

        /**
         * initialize the game panel during game start
         */
        public void init() {
            ArrayList<Hand> hands = game.getHandsOnTable();
            Hand topHand = hands.get(hands.size()-1);
            layeredCards.setupStatic(topHand);
        }
    }

    /**
     * represents layers of cards
     */
    public class LayeredCards extends JLayeredPane {
        CardList cardsSelected = new CardList();
        CardList cardsToPaint;

        /**
         * get the cards selected by players
         * @return cards selected by players
         */
        public CardList getCardsSelected() {
            return cardsSelected;
        }

        /**
         * setup cards that are hidden
         * @param amount
         */
        public void setupHidden(int amount) {
            removeAll();
            for (int i=0; i<amount; i++) {
                Image cardImage = getBlankCard();
                if (cardImage != null) {
                    ImageIcon cardIcon = new ImageIcon(cardImage);
                    JLabel cardLabel = new JLabel(cardIcon);
                    cardLabel.setBounds(15*i, 20,
                            cardIcon.getIconWidth(),
                            cardIcon.getIconHeight());
                    add(cardLabel, i);
                    moveToFront(cardLabel);
                }
            }
        }

        /**
         * setup cards that are clickable
         * @param cards the cards to show
         */
        public void setupClickable(CardList cards) {
            removeAll();
            cardsToPaint = cards;
            cardsSelected = new CardList();
            for(int i=0; i<cardsToPaint.size(); i++) {
                Card cardToPaint = cardsToPaint.getCard(i);
                Image cardImage = getCardImage(cardToPaint.getSuit(), cardToPaint.getRank());
                if (cardImage != null) {
                    ImageIcon cardIcon = new ImageIcon(cardImage);
                    JButton cardButton = new JButton(cardIcon);
                    cardButton.setBounds(15*i, 20,
                            cardIcon.getIconWidth(),
                            cardIcon.getIconHeight());
                    cardButton.addActionListener(
                            new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent actionEvent) {
                                    JButton buttonClicked = cardButton;
                                    Rectangle buttonBound = buttonClicked.getBounds();
                                    int rectangleX = (int) buttonBound.getLocation().getX();
                                    int rectangleY = (int) buttonBound.getLocation().getY();
                                    boolean cardAlreadySelected = false;
                                    for (int j=0; j<cardsSelected.size(); j++) {
                                        if(cardsSelected.getCard(j) == cardToPaint) {
                                            cardAlreadySelected = true;
                                        }
                                    }
                                    if (cardAlreadySelected) {
                                        cardButton.setLocation(rectangleX, rectangleY + 20);
                                        cardsSelected.removeCard(cardToPaint);
                                    } else {
                                        cardButton.setLocation(rectangleX, rectangleY - 20);
                                        cardsSelected.addCard(cardToPaint);
                                    }
                                    frame.repaint();
                                }
                            }
                    );
                    add(cardButton, i);
                    moveToFront(cardButton);
                }
            }
        }

        /**
         * setup cards that cannot be clicked
         * @param cards cards to show
         */
        public void setupStatic(CardList cards) {
            removeAll();
            cardsToPaint = cards;
            cardsSelected = new CardList();
            for(int i=0; i<cardsToPaint.size(); i++) {
                Card cardToPaint = cardsToPaint.getCard(i);
                Image cardImage = getCardImage(cardToPaint.getSuit(), cardToPaint.getRank());
                if (cardImage != null) {
                    ImageIcon cardIcon = new ImageIcon(cardImage);
                    JLabel cardLabel = new JLabel(cardIcon);
                    cardLabel.setBounds(15*i, 20,
                            cardIcon.getIconWidth(),
                            cardIcon.getIconHeight());
                    add(cardLabel, i);
                    moveToFront(cardLabel);
                }
            }
        }

        /**
         * constructs the layeredCards
         * @param cardList the list of cards
         */
        public LayeredCards(CardList cardList) {
            cardsToPaint = cardList;
        }
    }

    /**
     * get the image of a blank card
     * @return image of a blank card
     */
    Image getBlankCard() {
        String pathName = "images/cards/b.gif";

        URL resource = this.getClass().getResource(pathName);
        if (resource == null) {
            System.out.println("resource not found");
            return null;
        }

        try {
            BufferedImage cardImage = ImageIO.read(resource);
            return cardImage;
        } catch(java.io.IOException e) {
            System.out.println("card not found");
            return null;
        }
    }

    /**
     * send game status message to top right panel
     * @param message
     */
    void sendGameStatusMessage(String message) {
        gameStatusMessages += message + "\n";
        frame.repaint();
    }

    /**
     * get the image of a card
     * @param suit the suit of the card
     * @param rank the rank of the card
     * @return null if image is not found
     */
    Image getCardImage(int suit, int rank) {
        // order from low to high: Diamond, Clubs, Hearts, Spades
        String[] suitName = {"d", "c", "h", "s"};
        String[] rankName = {"a", "2", "3", "4", "5", "6", "7", "8", "9", "t", "j", "q", "k"};
        String pathName = "images/cards/" + rankName[rank] + suitName[suit] + ".gif";

        URL resource = this.getClass().getResource(pathName);
        if (resource == null) {
            System.out.println("resource not found");
            return null;
        }

        try {
            BufferedImage cardImage = ImageIO.read(resource);
            return cardImage;
        } catch(java.io.IOException e) {
            System.out.println("card not found");
            return null;
        }
    }

    /**
     * set the activePlayer
     * @param activePlayer an int value representing the index of the active player
     */
    @Override
    public void setActivePlayer(int activePlayer) {
        for(PlayerPanel panel : playerPanels) {
            panel.init(activePlayer);
        }
    }

    /**
     * repaint the GUI
     */
    @Override
    public void repaint() {

        if (!panelsHaveInitialized) {
            for(PlayerPanel panel : playerPanels) {
                panel.init();
            }
            panelsHaveInitialized = true;
        }

        frame.repaint();
    }

    /**
     * print message
     * @param msg the string to be printed to the message area of the card game user
     *            interface
     */
    @Override
    public void printMsg(String msg) {
        sendGameStatusMessage(msg);
    }

    /**
     * clear the message
     */
    @Override
    public void clearMsgArea() {}

    /**
     * reset the GUI
     */
    @Override
    public void reset() {}

    /**
     * enable the GUI
     */
    @Override
    public void enable() {}

    /**
     * disable the GUI
     */
    @Override
    public void disable() {}

    /**
     * prompt the active player
     */
    @Override
    public void promptActivePlayer() {
        handPanel.init();
        sendGameStatusMessage("player " + game.getCurrentPlayerIdx() + "'s turn:");

    }
}