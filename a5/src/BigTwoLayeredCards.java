import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URL;

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
            Image cardImage = getBlankCard();
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
            Image cardImage = getCardImage(cardToPaint.getSuit(), cardToPaint.getRank());
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
            Image cardImage = getCardImage(cardToPaint.getSuit(), cardToPaint.getRank());
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
     * get the image of a blank card
     * @return image of a blank card
     */
    Image getBlankCard() {
        String pathName = "images/cards/b.gif";

        URL resource = this.getClass().getResource(pathName);
        if (resource == null) {
            System.out.println("resource not found");
            return null;
        }

        try {
            BufferedImage cardImage = ImageIO.read(resource);
            return cardImage;
        } catch(java.io.IOException e) {
            System.out.println("card not found");
            return null;
        }
    }

    /**
     * get the image of a card
     * @param suit the suit of the card
     * @param rank the rank of the card
     * @return null if image is not found
     */
    Image getCardImage(int suit, int rank) {
        // order from low to high: Diamond, Clubs, Hearts, Spades
        String[] suitName = {"d", "c", "h", "s"};
        String[] rankName = {"a", "2", "3", "4", "5", "6", "7", "8", "9", "t", "j", "q", "k"};
        String pathName = "images/cards/" + rankName[rank] + suitName[suit] + ".gif";

        URL resource = this.getClass().getResource(pathName);
        if (resource == null) {
            System.out.println("resource not found");
            return null;
        }

        try {
            BufferedImage cardImage = ImageIO.read(resource);
            return cardImage;
        } catch(java.io.IOException e) {
            System.out.println("card not found");
            return null;
        }
    }
}
