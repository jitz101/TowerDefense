package entity.gui.shop;

import entity.PlayerTower;
import entity.gui.BasicButton;
import entity.gui.Money;
import main.Text;
import main.GamePanel;

import java.awt.*;

public class RateOfFireButton extends BasicButton {
    Shop shop = new Shop(gamePanel);
    PlayerTower playerTower;
    Money money;

    public RateOfFireButton(GamePanel gamePanel, Money money, PlayerTower playerTower) {
        super(gamePanel);
        this.money = money;
        this.playerTower = playerTower;

        this.imagePath = "/gui/shop/rateOfFire.png";
        this.hoverImagePath = "/gui/shop/rateOfFireHover.png";
        this.pressedImagePath = "/gui/shop/rateOfFirePressed.png";

        setDefaultValues(shop.shopPositionX + ts * 2 + ts / 2, shop.shopPositionY + ts * 2, 0);
        hitbox = setHitbox(x + 1000, y, ts * 2, ts * 2, false);
    }

    @Override
    protected void mouseReleasedEvent() {
        int cost = 1;

        if (money.moneyAmount >= cost && playerTower.reloadTime > 100) {
            System.out.println("RateOfFire+");
            money.moneyAmount -= cost;
            playerTower.reloadTime -= 100;
            System.out.println("Reload time: " + playerTower.reloadTime);
        }
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {
        shop.drawShopItem(g2, image, x, y, "Rate of", "Fire+", "100$", hitbox);
    }
}
