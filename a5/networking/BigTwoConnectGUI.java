import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * a GUI that allows user to connect to the server
 */
public class BigTwoConnectGUI {
    private static int frameWidth = 500;
    private static int frameHeight = 500;
    private JFrame frame;
    private BigTwoTextDisplay clientLog = new BigTwoTextDisplay();
    private ActionListener connectButtonListener;
    private String playerName = "";

    public BigTwoConnectGUI(ActionListener connectButtonListener) {
        this.connectButtonListener = connectButtonListener;

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setMinimumSize(new Dimension(300, 300));

        frame.setLayout(new BorderLayout());
        frame.add(getMainPanel(), BorderLayout.CENTER);

        frame.setVisible(true);

        frame.repaint();
    }

    public String getPlayerName() {
        return playerName;
    }

    public void dispose() {
        frame.dispose();
    }

    private JPanel getMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        mainPanel.add(getLeftPanel());
        mainPanel.add(new JScrollPane(clientLog));

        return mainPanel;
    }

    private JPanel getLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel prompt = new JLabel("Enter player name: ");

        leftPanel.add(prompt, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        JTextField field = new JTextField();

        field.setPreferredSize(new Dimension(100, 30));

        leftPanel.add(field, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        JButton connectButton = new JButton("Connect");
        connectButton.setSize(new Dimension(50, 50));
        connectButton.addActionListener(connectButtonListener);
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerName = field.getText();
                field.setText("");
                frame.repaint();
            }
        });

        leftPanel.add(connectButton, gbc);

        return leftPanel;
    }

    public void sendToLog(String message) {
        clientLog.addText(message + "\n");
        frame.repaint();
    }
}
