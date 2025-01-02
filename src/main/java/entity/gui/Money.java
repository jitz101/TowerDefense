package entity.gui;

import entity.Entity;
import main.GamePanel;
import main.Text;

import java.awt.*;

public class Money extends Entity {
    public int moneyAmount = 100;

    public Money(GamePanel gamePanel) {
        super(gamePanel);
    }

    public void addMoney(int reward) {
        moneyAmount += reward;
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {
        Text font = new Text();
        Font pixelFont = font.pixelFont(20);

        g2.setFont(pixelFont);
        g2.setColor(Color.WHITE);

        String text = moneyAmount + "$";

        FontMetrics metrics = g2.getFontMetrics(pixelFont);
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getAscent();
        int xString = gamePanel.screenWidth - textWidth - 10;
        int yString = textHeight + 15;

        g2.drawString(text, xString, yString);
    }
}
