import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BigTwoGUI implements CardGameUI {
    static int frameWidth = 800;
    static int frameHeight = 800;
    JFrame frame;
    JPanel bigTwoPanel;

    public BigTwoGUI(BigTwo game) {
        BigTwoPanel bigTwoPanel = new BigTwoPanel();

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setMinimumSize(new Dimension(300, 300));

        frame.add(bigTwoPanel);
        frame.setVisible(true);
    }

    public static class BigTwoPanel extends JPanel {
        BigTwoPanel() {
            setLayout(new GridBagLayout());

            /*
            leftPanel (x=0, y=0-1)
            topRightPanel (x=1, y=0)
            bottomRightPanel (x=1, y=1)
            bottomBar (x=0-1, y=2)
            */
            LeftPanel leftPanel = new LeftPanel();
            add(leftPanel, leftPanel.gbc);

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

            JPanel bottomRightPanel = new JPanel();
            bottomRightPanel.setBackground(Color.lightGray);
            bottomRightPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));

            GridBagConstraints bottomRightPanelConfig = new GridBagConstraints();
            bottomRightPanelConfig.fill = GridBagConstraints.BOTH;
            bottomRightPanelConfig.gridx = 1;
            bottomRightPanelConfig.gridy = 1;
            bottomRightPanelConfig.gridheight = 1;
            bottomRightPanelConfig.gridwidth = 1;
            bottomRightPanelConfig.weightx = 3;
            bottomRightPanelConfig.weighty = 8;

            add(bottomRightPanel, bottomRightPanelConfig);

            BottomBar bottomBar = new BottomBar();
            add(bottomBar, bottomBar.gbc);
        }
    }

    public static class BottomBar extends JPanel {
        public GridBagConstraints gbc = new GridBagConstraints();

        public BottomBar() {
            // gbc to be used by BigTwoPanel
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridheight = 1;
            gbc.gridwidth = 2;
            gbc.weightx = 1;
            gbc.weighty = 1;

            setBorder(BorderFactory.createLineBorder(Color.darkGray));

            JButton playButton = new JButton();
            playButton.setText("Play");

            JButton passButton = new JButton();
            passButton.setText("Pass");

            JLabel messagePrompt = new JLabel();
            messagePrompt.setText("Message: ");

            JTextField messageInput = new JTextField();

            add(playButton);
            add(passButton);
            add(messagePrompt);
            add(messageInput);
        }
    }

    public static class LeftPanel extends JPanel {

        public GridBagConstraints gbc = new GridBagConstraints();

        public LeftPanel() {
            // gbc to be used by BigTwoPanel
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridheight = 2;
            gbc.gridwidth = 1;
            gbc.weightx = 4;
            gbc.weighty = 16;

            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.gridx = 0;

            PlayerPanel[] panelList = {
                    new PlayerPanel(), new PlayerPanel(), new PlayerPanel(),
                    new PlayerPanel(), new PlayerPanel()
            };

            for (int i=0; i<5; i++) {
                gbc.gridy = i;
                panelList[i].setBorder(BorderFactory.createLineBorder(Color.darkGray));
                add(panelList[i], gbc);
            }
        }
    }

    public static class PlayerPanel extends JPanel {
        public PlayerPanel() {
            JButton button = new JButton();
            button.setText("Hi");
            add(button);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
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

    public static class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("button pressed");
        }
    }
}