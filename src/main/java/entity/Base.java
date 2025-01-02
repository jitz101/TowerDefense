package entity;

import main.GamePanel;

import java.awt.*;

public class Base extends Entity {

    public Base(GamePanel gamePanel) {
        super(gamePanel);

        setDefaultValues(gamePanel.centerTileX, gamePanel.centerTileY, 0);

        hitbox = setHitbox(x, y, gamePanel.tileSize, gamePanel.tileSize, false);
    }

    public void update() {
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);

        g2.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);

        g2.setColor(Color.RED);
        g2.draw(hitbox);
    }
}
