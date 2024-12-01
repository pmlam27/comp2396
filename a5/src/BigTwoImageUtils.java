import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.security.InvalidParameterException;

public class BigTwoImageUtils {

    public static Image getImageByPath(String pathName) {
        BigTwoImageUtils context = new BigTwoImageUtils();
        URL resource = context.getClass().getResource(pathName);
        if (resource == null) {
            System.out.println("resource not found");
            return null;
        }

        try {
            BufferedImage cardImage = ImageIO.read(resource);
            return cardImage;
        } catch(java.io.IOException e) {
            System.out.println("image not found");
            return null;
        }
    }

    /**
     * get the image of a card
     * @param suit the suit of the card
     * @param rank the rank of the card
     * @return null if image is not found
     */
    public static Image getCardImage(int suit, int rank) {
        // order from low to high: Diamond, Clubs, Hearts, Spades
        String[] suitName = {"d", "c", "h", "s"};
        String[] rankName = {"a", "2", "3", "4", "5", "6", "7", "8", "9", "t", "j", "q", "k"};
        String pathName = "images/cards/" + rankName[rank] + suitName[suit] + ".gif";

        BigTwoImageUtils context = new BigTwoImageUtils();
        URL resource = context.getClass().getResource(pathName);
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
     * get the image of a blank card
     * @return image of a blank card
     */
    public static Image getBlankCard() {
        String pathName = "images/cards/b.gif";
        return getImageByPath(pathName);
    }

    /**
     * get the image of player avatar
     * @param playerId player id (1-4)
     * @return image of avatar
     */
    public static Image getPlayerAvatar(int playerId) {

        if (playerId == 0) {
            return getImageByPath("images/avatars/player1.png");
        } else if (playerId == 1) {
            return getImageByPath("images/avatars/player2.png");
        } else if (playerId == 2) {
            return getImageByPath("images/avatars/player3.png");
        } else if (playerId == 3) {
            return getImageByPath("images/avatars/player4.png");
        } else {
            throw new InvalidParameterException("player id invalid");
        }
    }
}
