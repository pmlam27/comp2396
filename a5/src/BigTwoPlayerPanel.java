import javax.swing.*;
import java.awt.*;

public class BigTwoPlayerPanel extends JPanel {
    private int playerId;
    private CardList cardsInHand;
    private BigTwoLayeredCards layeredCards;
    private JLabel playerNameLabel = new JLabel("");

    private JFrame frame;

    public int[] getCardsSelected() {
        return layeredCards.getCardsSelected();
    }

    public void setPlayerLabel(int activePlayerId) {
        if(playerId == activePlayerId) {
            playerNameLabel.setText("You");
        } else {
            playerNameLabel.setText("Player " + playerId);
        }
    }

    public void setClickableCards(CardList cards) {
        layeredCards.setupClickable(cards);
        frame.repaint();
    }

    public void setHiddenCards(CardList cards) {
        layeredCards.setupHidden(cards.size());
        frame.repaint();
    }

    /**
     * constructs the player panel
     * @param playerId the id of player
     */
    public BigTwoPlayerPanel(int playerId, JFrame frame) {
        this.frame = frame;

        this.playerId = playerId;
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;

        Image playerImage = BigTwoImageUtils.getPlayerAvatar(playerId).getScaledInstance(70, 70, Image.SCALE_DEFAULT);
        JLabel playerAvatar = new JLabel(new ImageIcon(playerImage));
        add(playerAvatar, gbc);

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.weighty = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(playerNameLabel, gbc);

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
