import javax.swing.*;
import java.awt.event.*;

public class BigTwoGUI implements CardGameUI {
    JFrame frame;

    public BigTwoGUI(BigTwo game) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("click me");
        ButtonListener buttonListener = new ButtonListener();
        button.addActionListener(buttonListener);

        frame.add(button);

        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        BigTwoGUI gui = new BigTwoGUI(null);
    }

    /**
     * Sets the index of the active player (i.e., the current player).
     *
     * @param activePlayer an int value representing the index of the active player
     */
    @Override
    public void setActivePlayer(int activePlayer) {

    }

    /**
     * Repaints the user interface.
     */
    @Override
    public void repaint() {

    }

    /**
     * Prints the specified string to the message area of the card game user
     * interface.
     *
     * @param msg the string to be printed to the message area of the card game user
     *            interface
     */
    @Override
    public void printMsg(String msg) {

    }

    /**
     * Clears the message area of the card game user interface.
     */
    @Override
    public void clearMsgArea() {

    }

    /**
     * Resets the card game user interface.
     */
    @Override
    public void reset() {

    }

    /**
     * Enables user interactions.
     */
    @Override
    public void enable() {

    }

    /**
     * Disables user interactions.
     */
    @Override
    public void disable() {

    }

    /**
     * Prompts active player to select cards and make his/her move.
     */
    @Override
    public void promptActivePlayer() {

    }

    public static class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("button pressed");
        }
    }
}