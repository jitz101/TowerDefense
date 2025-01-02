package entity.gui.shop;

import entity.Entity;
import main.GamePanel;
import main.Text;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Shop extends Entity {
    int ts = gamePanel.tileSize;
    int sc = gamePanel.maxScreenCol;
    int sr = gamePanel.maxScreenRow;

    int shopPositionX = ts * (sc / 4);
    int shopPositionY = ts * (sr / 6);

    public Shop(GamePanel gamePanel) {
        super(gamePanel);

        setDefaultValues(shopPositionX, shopPositionY, 0);
        getImage("/gui/shop/shop.png");
    }

    public void drawShopItem(Graphics2D g2, BufferedImage image, int x, int y, String text1, String text2, String text3, Rectangle2D hitbox) {
        Text fonts = new Text();
        int size = 23;
        int lineBreak = size + 5;
        int textX = x + ts * 2 + ts / 2;
        int textY = y + ts / 2 + ts / 5;
        Font pixelFont = fonts.pixelFont(size);
        g2.setFont(pixelFont);
        g2.setColor(Color.BLACK);

        if (ShopButton.shopSignal) {
            g2.drawImage(image, x, y, ts * 2, ts * 2, null);
            hitbox.setRect(x, y, ts * 2, ts * 2);
            g2.drawString(text1, textX, textY);
            g2.drawString(text2, textX, textY + lineBreak);
            g2.drawString(text3, textX, textY + lineBreak * 2);
        } else {
            hitbox.setRect(x + 1000, y + 1000, ts * 2, ts * 2);
        }
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {
        if (ShopButton.shopSignal) {
            g2.drawImage(image, x, y, ts * (sc / 2), ts * (sc / 2), null);
        }
    }
}
