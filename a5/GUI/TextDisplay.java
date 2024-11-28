import javax.swing.*;
import java.awt.*;

public class TextDisplay extends JTextArea {
    private String text = "";
    public void addText(String message) {
        text = text + message;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setText(text);
    }


}
