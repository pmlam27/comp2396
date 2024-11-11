import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.awt.GridBagConstraints.BOTH;

public class BigTwoGUI implements CardGameUI {
    static int frameWidth = 600;
    static int frameHeight = 600;
    JFrame frame;
    JPanel bigTwoPanel;

    public BigTwoGUI(BigTwo game) {
        BigTwoPanel bigTwoPanel = new BigTwoPanel();

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);

        frame.add(bigTwoPanel);
        frame.setVisible(true);
    }

    public static class BigTwoPanel extends JPanel {
        BigTwoPanel() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.weightx = 1;
            gbc.weighty = 1;

            JPanel leftPanel = new JPanel();
            leftPanel.setBackground(Color.darkGray);
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(leftPanel);

            JPanel rightPanel = new JPanel();
            rightPanel.setBackground(Color.lightGray);
            gbc.gridx = 1;
            gbc.gridy = 0;
            add(rightPanel);

            setBackground(Color.white);
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