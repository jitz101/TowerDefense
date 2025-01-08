package entity.gui;

import entity.Entity;
import main.GamePanel;
import main.Text;

import java.awt.*;

public class Wave extends Entity {
    public static int currentWave = 0;

    public Wave(GamePanel gamePanel) {
        super(gamePanel);
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {
        Text font = new Text();
        Font pixelFont = font.pixelFont(20);

        g2.setFont(pixelFont);
        g2.setColor(Color.WHITE);

        String text = "Wave:" + currentWave;

        FontMetrics metrics = g2.getFontMetrics(pixelFont);
        int textHeight = metrics.getAscent();
        int xString = 10;
        int yString = textHeight + 15;

        g2.drawString(text, xString, yString);
    }
}
