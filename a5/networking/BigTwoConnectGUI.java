import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * a GUI that allows user to connect to the server
 */
public class BigTwoConnectGUI {
    private static int frameWidth = 400;
    private static int frameHeight = 400;
    private JFrame frame;
    private BigTwoTextDisplay clientLog = new BigTwoTextDisplay();

    public BigTwoConnectGUI() {

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setMinimumSize(new Dimension(300, 300));

        frame.setLayout(new BorderLayout());
        frame.add(getMainPanel(), BorderLayout.CENTER);

        frame.setVisible(true);

        frame.repaint();
    }

    private JPanel getMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));
        mainPanel.add(getLeftPanel());
        mainPanel.add(new JScrollPane(clientLog));

        return mainPanel;
    }

    private JPanel getLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(4, 1));
        leftPanel.add(new JLabel("Enter player name: "));
        leftPanel.add(new JLabel("..."));
        leftPanel.add(new JLabel("..."));
        leftPanel.add(new JButton("Connect"));

        return leftPanel;
    }

    public void sendToLog(String message) {
        clientLog.addText(message);
        frame.repaint();
    }
}
