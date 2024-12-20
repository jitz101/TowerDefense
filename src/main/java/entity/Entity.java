package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {
    public int x, y;
    public double xD, yD;
    public int speed;
    public double angle;

    public BufferedImage image;
    protected GamePanel gamePanel;

    public Rectangle2D hitbox;

    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setDefaultValues(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void setDefaultValues(double xD, double yD, int speed) {
        this.xD = xD;
        this.yD = yD;
        this.speed = speed;
    }

    public void getImage(String imagePath) {
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Rectangle2D drawHitbox(int x, int y, int width, int height, boolean moving) {
        return moving ?
                new Rectangle2D.Double(x - gamePanel.scale, y - gamePanel.scale, width * gamePanel.scale, height * gamePanel.scale)
                :
                new Rectangle2D.Double(x, y, width, height);
    }
}
