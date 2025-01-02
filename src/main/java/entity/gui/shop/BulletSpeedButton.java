package entity.gui.shop;

import entity.PlayerTower;
import entity.gui.BasicButton;
import entity.gui.Money;
import main.GamePanel;

import java.awt.*;

public class BulletSpeedButton extends BasicButton {
    Shop shop = new Shop(gamePanel);
    PlayerTower playerTower;
    Money money;

    public BulletSpeedButton(GamePanel gamePanel, Money money, PlayerTower playerTower) {
        super(gamePanel);
        this.money = money;
        this.playerTower = playerTower;

        this.imagePath = "/gui/shop/bulletSpeed.png";
        this.hoverImagePath = "/gui/shop/bulletSpeedHover.png";
        this.pressedImagePath = "/gui/shop/bulletSpeedPressed.png";

        setDefaultValues(shop.shopPositionX + ts * 2 + ts / 2, shop.shopPositionY + ts * 8, 0);
        hitbox = setHitbox(x, y, ts * 2, ts * 2, false);
    }

    @Override
    protected void mouseReleasedEvent() {
        int cost = 1;

        if (money.moneyAmount >= cost) {
            System.out.println("BulletSpeed+");
            money.moneyAmount -= cost;
            playerTower.projectileSpeed += 2;
        }
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {
        shop.drawShopItem(g2, image, x, y, "Bullet", "Speed+", "50$", hitbox);
    }
}
