import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.security.InvalidParameterException;

public class BigTwoLayeredCards extends JLayeredPane {
    CardList cardsSelected = new CardList();
    CardList cardsToPaint;
    JFrame frame;

    /**
     * get the cards selected by players
     * @return cards selected by players
     */
    public CardList getCardsSelected() {
        return cardsSelected;
    }

    /**
     * constructs the layeredCards
     * @param cardList the list of cards
     */
    public BigTwoLayeredCards(CardList cardList, JFrame frame) {
        cardsToPaint = cardList;
        this.frame = frame;
    }

    /**
     * setup cards that are hidden
     * @param amount
     */
    public void setupHidden(int amount) {
        removeAll();
        for (int i=0; i<amount; i++) {
            Image cardImage = BigTwoImageUtils.getBlankCard();
            if (cardImage != null) {
                ImageIcon cardIcon = new ImageIcon(cardImage);
                JLabel cardLabel = new JLabel(cardIcon);
                cardLabel.setBounds(15*i, 20,
                        cardIcon.getIconWidth(),
                        cardIcon.getIconHeight());
                add(cardLabel, i);
                moveToFront(cardLabel);
            }
        }
    }
    /**
     * setup cards that are clickable
     * @param cards the cards to show
     */
    public void setupClickable(CardList cards) {
        removeAll();
        cardsToPaint = cards;
        cardsSelected = new CardList();
        for(int i=0; i<cardsToPaint.size(); i++) {
            Card cardToPaint = cardsToPaint.getCard(i);
            Image cardImage = BigTwoImageUtils.getCardImage(cardToPaint.getSuit(), cardToPaint.getRank());
            if (cardImage != null) {
                ImageIcon cardIcon = new ImageIcon(cardImage);
                JButton cardButton = new JButton(cardIcon);
                cardButton.setBounds(15*i, 20,
                        cardIcon.getIconWidth(),
                        cardIcon.getIconHeight());
                cardButton.addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                                JButton buttonClicked = cardButton;
                                Rectangle buttonBound = buttonClicked.getBounds();
                                int rectangleX = (int) buttonBound.getLocation().getX();
                                int rectangleY = (int) buttonBound.getLocation().getY();
                                boolean cardAlreadySelected = false;
                                for (int j=0; j<cardsSelected.size(); j++) {
                                    if(cardsSelected.getCard(j) == cardToPaint) {
                                        cardAlreadySelected = true;
                                    }
                                }
                                if (cardAlreadySelected) {
                                    cardButton.setLocation(rectangleX, rectangleY + 20);
                                    cardsSelected.removeCard(cardToPaint);
                                } else {
                                    cardButton.setLocation(rectangleX, rectangleY - 20);
                                    cardsSelected.addCard(cardToPaint);
                                }
                                frame.repaint();
                            }
                        }
                );
                add(cardButton, i);
                moveToFront(cardButton);
            }
        }
    }

    /**
     * setup cards that cannot be clicked
     * @param cards cards to show
     */
    public void setupStatic(CardList cards) {
        removeAll();
        cardsToPaint = cards;
        cardsSelected = new CardList();
        for(int i=0; i<cardsToPaint.size(); i++) {
            Card cardToPaint = cardsToPaint.getCard(i);
            Image cardImage = BigTwoImageUtils.getCardImage(cardToPaint.getSuit(), cardToPaint.getRank());
            if (cardImage != null) {
                ImageIcon cardIcon = new ImageIcon(cardImage);
                JLabel cardLabel = new JLabel(cardIcon);
                cardLabel.setBounds(15*i, 20,
                        cardIcon.getIconWidth(),
                        cardIcon.getIconHeight());
                add(cardLabel, i);
                moveToFront(cardLabel);
            }
        }
    }

}
