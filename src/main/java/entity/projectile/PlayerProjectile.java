package entity.projectile;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class PlayerProjectile extends Entity {
    private double angle;

    public PlayerProjectile(GamePanel gamePanel, int x, int y, double angle) {
        super(gamePanel);
        setDefaultValues((double) x, (double) y, 5);
        this.angle = angle;

        getImage("/projectile/playerProjectile.png");
        hitbox = drawHitbox((int) xD, (int) yD, 2, 2, true);
    }

    public void update() {
        xD += speed * Math.cos(angle);
        yD += speed * Math.sin(angle);

        hitbox.setRect(xD - gamePanel.scale, yD - gamePanel.scale, hitbox.getWidth(), hitbox.getHeight());
    }

    public void draw(Graphics2D g2) {
        double centerX = xD - gamePanel.tileSize / 2;
        double centerY = yD - gamePanel.tileSize / 2;

        g2.drawImage(image, (int)centerX, (int)centerY, gamePanel.tileSize, gamePanel.tileSize, null);

        g2.setColor(Color.RED);
        g2.draw(hitbox);
    }
}
