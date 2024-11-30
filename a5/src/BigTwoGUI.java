import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BigTwoGUI {
    private static int frameWidth = 800;
    private static int frameHeight = 800;
    private JFrame frame;
    private TextDisplay textDisplay = new TextDisplay();

    public BigTwoGUI() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setMinimumSize(new Dimension(300, 300));

        frame.setLayout(new BorderLayout());
        frame.add(getTopBar(), BorderLayout.PAGE_START);
        frame.add(getMainPanel(), BorderLayout.CENTER);
        frame.add(getBottomBar(), BorderLayout.PAGE_END);

        frame.setVisible(true);

        frame.repaint();
    }

    public static void main(String[] args) {
        BigTwoGUI gui = new BigTwoGUI();
    }

    private JPanel getTopBar() {
        JPanel topBar = new JPanel();
        topBar.add(new JLabel("top"));

        return topBar;
    }

    private JPanel getBottomBar() {
        JPanel bottomBar = new JPanel();
        bottomBar.setLayout(new GridLayout(1, 4));

        bottomBar.add(new JButton("play"));
        bottomBar.add(new JButton("pass"));
        bottomBar.add(new JLabel("message"));
        bottomBar.add(new JTextField(""));

        return bottomBar;
    }


    private JPanel getMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));

        mainPanel.add(getLeftPanel());
        mainPanel.add(textDisplay);

        return mainPanel;
    }

    private JPanel getLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(5, 1));

        leftPanel.add(new BigTwoPlayerPanel(0, frame));
        leftPanel.add(new BigTwoPlayerPanel(1, frame));
        leftPanel.add(new BigTwoPlayerPanel(2, frame));
        leftPanel.add(new BigTwoPlayerPanel(3, frame));
        leftPanel.add(new JLabel("Hello"));

        return leftPanel;
    }



    private JButton getAddTextButton() {
        JButton addTextButton = new JButton();
        addTextButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textDisplay.addText("hello\n");
                        frame.repaint();
                    }
                }
        );
        return addTextButton;
    }


}
