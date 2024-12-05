import javax.swing.*;
import java.awt.*;

public class BigTwoTextDisplay extends JTextArea {
    private String text = "";

    public BigTwoTextDisplay() {
        setEditable(false);
    }

    public void addText(String message) {
        text = text + message;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setText(text);
    }


}
