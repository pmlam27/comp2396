import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BigTwoGUI implements CardGameUI {
    private static int frameWidth = 800;
    private static int frameHeight = 800;
    private JFrame frame;
    private BigTwoTextDisplay gameLog = new BigTwoTextDisplay();
    private BigTwoTextDisplay playerMessages = new BigTwoTextDisplay();
    private ArrayList<BigTwoPlayerPanel> playerPanels = new ArrayList<BigTwoPlayerPanel>();
    private BigTwoHandPanel handPanel;

    private BigTwo game;
    private int activePlayerId;

    public BigTwoGUI(BigTwo game) {
        this.game = game;

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setMinimumSize(new Dimension(300, 300));

        frame.setLayout(new BorderLayout());
        frame.add(getTopBar(), BorderLayout.PAGE_START);
        frame.add(getMainPanel(), BorderLayout.CENTER);
        frame.add(getBottomBar(), BorderLayout.PAGE_END);

        frame.setVisible(true);

        frame.repaint();
    }

    private JMenuBar getTopBar() {
        JPanel topBar = new JPanel();

        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Game");
        JMenuItem restartItem = new JMenuItem("Restart");
        // TODO: restart logic
        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        //

        menu.add(restartItem);
        menu.add(quitItem);

        menuBar.add(menu);
        return menuBar;
    }

    private JPanel getBottomBar() {
        JPanel bottomBar = new JPanel();
        bottomBar.setLayout(new GridLayout(1, 4));

        JButton playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] cardsSelected = playerPanels.get(activePlayerId).getCardsSelected();
                if(cardsSelected.length != 0) {
                    game.makeMove(activePlayerId, playerPanels.get(activePlayerId).getCardsSelected());
                }
            }
        });

        JButton passButton = new JButton("Pass");
        passButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.makeMove(activePlayerId, new int[0]);
            }
        });

        bottomBar.add(playButton);
        bottomBar.add(passButton);

        bottomBar.add(new JLabel("message"));
        bottomBar.add(getMessageInputField());

        return bottomBar;
    }


    private JPanel getMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));

        mainPanel.add(getLeftPanel());
        mainPanel.add(getRightPanel());

        return mainPanel;
    }

    private JPanel getLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(5, 1));

        playerPanels.add(new BigTwoPlayerPanel(0, frame));
        playerPanels.add(new BigTwoPlayerPanel(1, frame));
        playerPanels.add(new BigTwoPlayerPanel(2, frame));
        playerPanels.add(new BigTwoPlayerPanel(3, frame));

        for (BigTwoPlayerPanel panel : playerPanels) {
            leftPanel.add(panel);
        }

        handPanel = new BigTwoHandPanel(frame);
        leftPanel.add(handPanel);

        return leftPanel;
    }

    private JPanel getRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(2, 1));

        rightPanel.add(new JScrollPane(gameLog));
        rightPanel.add(new JScrollPane(playerMessages));
        return rightPanel;
    }

    private JTextField getMessageInputField() {
        JTextField messageInput = new JTextField();
        messageInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerMessages.addText(messageInput.getText() + "\n");
                messageInput.setText("");
                frame.repaint();
            }
        });
        return messageInput;
    }

    private void sendToGameLog(String message) {
        gameLog.addText(message);
        frame.repaint();
    }

    private void refreshLeftPanel() {
        for(int i=0; i<4; i++) {
            CardList playerCards = game.getPlayerList().get(i).getCardsInHand();
            playerPanels.get(i).setPlayerLabel(activePlayerId);
            if(i == activePlayerId) {
                playerPanels.get(i).setClickableCards(playerCards);
            } else {
                playerPanels.get(i).setHiddenCards(playerCards);
            }
        }
    }

    @Override
    public void setActivePlayer(int activePlayer) {
        activePlayerId = activePlayer;
    }

    @Override
    public void repaint() {

    }

    @Override
    public void printMsg(String msg) {
        sendToGameLog(msg);
    }

    @Override
    public void clearMsgArea() {

    }

    @Override
    public void reset() {

    }

    @Override
    public void enable() {

    }

    @Override
    public void disable() {

    }

    @Override
    public void promptActivePlayer() {
        activePlayerId = game.getCurrentPlayerIdx();
        sendToGameLog("Player " + activePlayerId + "'s turn:");
        refreshLeftPanel();

        ArrayList<Hand> hands = game.getHandsOnTable();
        if(hands.size() > 0) {
            Hand topHand = hands.get(hands.size()-1);
            handPanel.updateLastPlayedHand(topHand);
        }
    }
}
