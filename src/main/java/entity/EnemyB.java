package entity;

import main.GamePanel;

import java.awt.*;

public class EnemyB extends Entity {
    private double angle;

    public EnemyB(GamePanel gamePanel) {
        super(gamePanel);
        setDefaultValues(0.0, 0.0, 2);
        setSpawnPosition();

        this.angle = angleToCenter();

        getImage("/enemy/EnemyB.png");
        hitbox = drawHitbox((int) xD, (int) yD, 12, 12, true);
    }

    private void setSpawnPosition() {
        int screenSide = (int) (Math.random() * 4);

        switch (screenSide) {
            case 0:
                this.xD = Math.random() * gamePanel.screenWidth;
                this.yD = -50;
                break;
            case 1:
                this.xD = gamePanel.screenWidth + 50;
                this.yD = Math.random() * gamePanel.screenHeight;
                break;
            case 2:
                this.xD = Math.random() * gamePanel.screenWidth;
                this.yD = gamePanel.screenHeight + 50;
                break;
            case 3:
                this.xD = -50;
                this.yD = Math.random() * gamePanel.screenHeight;
                break;
        }
    }

    private double angleToCenter() {
        double centerX = xD - (double) gamePanel.tileSize / 2;
        double centerY = yD - (double) gamePanel.tileSize / 2;

        return angle = Math.atan2(gamePanel.centerHeight - centerY, gamePanel.centerWidth - centerX);
    }

    public void update() {
        xD += speed * Math.cos(angle);
        yD += speed * Math.sin(angle);

        hitbox.setRect(xD - 39 - gamePanel.scale, yD - 39 - gamePanel.scale, hitbox.getWidth(), hitbox.getHeight());
    }

    public void draw(Graphics2D g2) {
        double centerX = xD - (double) gamePanel.tileSize / 2;
        double centerY = yD - (double) gamePanel.tileSize / 2;

        var originalTransform = g2.getTransform();

        g2.translate(centerX, centerY);
        g2.rotate(angle);
        g2.translate(-gamePanel.tileSize / 2, -gamePanel.tileSize / 2);

        g2.drawImage(image, 0, 0, gamePanel.tileSize, gamePanel.tileSize, null);

        g2.setTransform(originalTransform);

        g2.setColor(Color.RED);
        g2.draw(hitbox);
    }
}
