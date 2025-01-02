package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

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
        InputStream resourceStream = getClass().getResourceAsStream(imagePath);
        if (resourceStream == null) {
            System.err.println("Resource not found: " + imagePath);
        } else {
            try {
                image = ImageIO.read(resourceStream);
            } catch (IOException e) {
                System.err.println("Error reading the image: " + e.getMessage());
            }
        }
    }

    public Rectangle2D setHitbox(int x, int y, int width, int height, boolean moving) {
        return moving ?
                new Rectangle2D.Double(x - gamePanel.scale, y - gamePanel.scale, width * gamePanel.scale, height * gamePanel.scale)
                :
                new Rectangle2D.Double(x, y, width, height);
    }
}
