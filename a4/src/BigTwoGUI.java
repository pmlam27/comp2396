import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class BigTwoGUI implements CardGameUI {
    static int frameWidth = 800;
    static int frameHeight = 800;
    static ArrayList<JLabel> cardPictures;
    JFrame frame;
    static String userMessages = "";

    public BigTwoGUI(BigTwo game) {
        BigTwoPanel bigTwoPanel = new BigTwoPanel();

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setMinimumSize(new Dimension(300, 300));

        frame.add(bigTwoPanel);
        frame.setVisible(true);
    }

    public class BigTwoPanel extends JPanel {
        BigTwoPanel() {
            setLayout(new GridBagLayout());

            /*
            leftPanel (x=0, y=0-1)
            topRightPanel (x=1, y=0)
            bottomRightPanel (x=1, y=1)
            bottomBar (x=0-1, y=2)
            */
            LeftPanel leftPanel = new LeftPanel();
            add(leftPanel, leftPanel.getGbc());

            JPanel topRightPanel = new JPanel();
            topRightPanel.setBackground(Color.lightGray);
            topRightPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));

            GridBagConstraints topRightPanelConfig = new GridBagConstraints();
            topRightPanelConfig.fill = GridBagConstraints.BOTH;
            topRightPanelConfig.gridx = 1;
            topRightPanelConfig.gridy = 0;
            topRightPanelConfig.gridheight = 1;
            topRightPanelConfig.gridwidth = 1;
            topRightPanelConfig.weightx = 3;
            topRightPanelConfig.weighty = 8;

            add(topRightPanel, topRightPanelConfig);

            BottomRightPanel bottomRightPanel = new BottomRightPanel();
            add(bottomRightPanel, bottomRightPanel.getGbc());

            BottomBar bottomBar = new BottomBar();
            add(bottomBar, bottomBar.getGbc());
        }
    }

    public class BottomBar extends JPanel {
        public GridBagConstraints getGbc() {
            GridBagConstraints gbc = new GridBagConstraints();
            // gbc to be used by BigTwoPanel
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridheight = 1;
            gbc.gridwidth = 2;
            gbc.weightx = 1;
            gbc.weighty = 1;
            return gbc;
        }

        public BottomBar() {
            setLayout(new GridBagLayout());
            setBorder(BorderFactory.createLineBorder(Color.darkGray));

            GridBagConstraints subGbc = new GridBagConstraints();
            // subGbc.fill = GridBagConstraints.BOTH;
            subGbc.weightx = 1;
            subGbc.weighty = 1;

            JButton playButton = new JButton();
            playButton.setText("Play");
            subGbc.gridx = 0;
            add(playButton, subGbc);

            JButton passButton = new JButton();
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

    public class BottomRightPanel extends JPanel {

        static JTextArea messageArea = new JTextArea();
        public GridBagConstraints getGbc() {
            GridBagConstraints gbc = new GridBagConstraints();
            // gbc to be used by BigTwoPanel
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            gbc.weightx = 3;
            gbc.weighty = 8;
            return gbc;
        }

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

    public class LeftPanel extends JPanel {
        public GridBagConstraints getGbc() {
            GridBagConstraints gbc = new GridBagConstraints();
            // gbc to be used by BigTwoPanel
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridheight = 2;
            gbc.gridwidth = 1;
            gbc.weightx = 4;
            gbc.weighty = 16;
            return gbc;
        }

        public LeftPanel() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.gridx = 0;

            PlayerPanel[] panelList = {
                    new PlayerPanel(), new PlayerPanel(),
                    new PlayerPanel(), new PlayerPanel()
            };

            for (int i=0; i<4; i++) {
                gbc.gridy = i;
                panelList[i].setBorder(BorderFactory.createLineBorder(Color.darkGray));
                add(panelList[i], gbc);
            }

            HandPanel handPanel = new HandPanel();
            gbc.gridy = 4;
            handPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
            add(handPanel, gbc);

        }
    }

    public class PlayerPanel extends JPanel {
        public PlayerPanel() {
            JButton button = new JButton();
            button.setText("Player panel");
            add(button);


        }
    }

    public class HandPanel extends JPanel {
        public HandPanel() {
//            JButton button = new JButton();
//            button.setText("Hand panel");
//            add(button);

            CardList cardList = new CardList();
            cardList.addCard(new BigTwoCard(0, 0));
            ImageIcon cardIcon = getCardIcon(0, 0);
            if (cardIcon != null) {
                add(new JLabel(cardIcon));
            }

           //  LayeredCards cardComponent = new LayeredCards(cardList);
        }
    }

    public class LayeredCards extends JLayeredPane {
        public LayeredCards(CardList cardList) {

        }
    }

    /**
     *
     * @param suit
     * @param rank
     * @return null if image is not found
     */
    ImageIcon getCardIcon(int suit, int rank) {
        // order from low to high: Diamond, Clubs, Hearts, Spades
        String[] suitName = {"d", "c", "h", "s"};
        String[] rankName = {"a", "2", "3", "4", "5", "6", "7", "8", "9", "t", "j", "q", "k"};
        String pathName = "images/cards/" + rankName[rank] + suitName[suit] + ".gif";

        URL resource = this.getClass().getResource(pathName);
        if (resource == null) {
            return null;
        }

        try {
            BufferedImage cardImage = ImageIO.read(resource);
            return new ImageIcon(cardImage);
        } catch(java.io.IOException e) {
            System.out.println("card not found");
            return null;
        }
    }

    public static void main(String[] args) {
        BigTwoGUI gui = new BigTwoGUI(null);
    }

    @Override
    public void setActivePlayer(int activePlayer) {

    }

    @Override
    public void repaint() {}

    @Override
    public void printMsg(String msg) {}

    @Override
    public void clearMsgArea() {}

    @Override
    public void reset() {}

    @Override
    public void enable() {}

    @Override
    public void disable() {}

    @Override
    public void promptActivePlayer() {}
}