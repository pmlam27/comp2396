import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
            gbc.fill = GridBagConstraints.BOTH;

            JPanel leftPanel = new JPanel();
            leftPanel.setBackground(Color.lightGray);
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(leftPanel, gbc);

            JPanel rightPanel = new JPanel();
            rightPanel.setBackground(Color.white);
            gbc.weightx = 2;
            gbc.weighty = 1;
            gbc.gridx = 1;
            gbc.gridy = 0;
            add(rightPanel, gbc);

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