package entity.gui;

import entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Money extends Entity {
    int money;

    public Money(GamePanel gamePanel) {
        super(gamePanel);
    }

    public void addMoney(int reward) {
        money += reward;
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {
        Font pixelFont;
        try {
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf")))
                    .deriveFont(20f);
        } catch (FontFormatException | IOException e) {
            System.err.println("Font Error: " + e.getMessage());
            pixelFont = new Font("Monospaced", Font.PLAIN, 20);
        }

        g2.setFont(pixelFont);
        g2.setColor(Color.WHITE);

        String text = money + "$";

        FontMetrics metrics = g2.getFontMetrics(pixelFont);
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getAscent();
        int xString = gamePanel.screenWidth - textWidth - 10;
        int yString = textHeight + 15;

        g2.drawString(text, xString, yString);
    }
}
