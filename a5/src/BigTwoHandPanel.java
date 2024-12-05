import javax.swing.*;
import java.awt.*;

public class BigTwoHandPanel extends JPanel {
    private CardList cardsInHand;
    private BigTwoLayeredCards layeredCards;
    private JLabel playedByLabel = new JLabel("played by ...");

    public void updateLastPlayedHand(CardList cards) {
        layeredCards.setupStatic(cards);
    }

    /**
     * constructs the player panel
     */
    public BigTwoHandPanel(JFrame frame) {

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;

        // Image playerImage = BigTwoImageUtils.getPlayerAvatar(playerId).getScaledInstance(70, 70, Image.SCALE_DEFAULT);
        JLabel handAvatar = new JLabel(new ImageIcon(BigTwoImageUtils.getBlankCard()));
        add(handAvatar, gbc);

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.weighty = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(playedByLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridheight = 2;
        gbc.weightx = 4;
        gbc.weighty = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        layeredCards = new BigTwoLayeredCards(new CardList(), frame);
        add(layeredCards, gbc);
    }
}
