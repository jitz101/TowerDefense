package entity.gui.shop;

import entity.gui.BasicButton;
import entity.gui.Money;
import main.GamePanel;

import java.awt.*;

public class DamageButton extends BasicButton {
    Shop shop = new Shop(gamePanel);
    Money money;
    int cost = 1000;

    public DamageButton(GamePanel gamePanel, Money money) {
        super(gamePanel);
        this.money = money;

        this.imagePath = "/gui/shop/damage.png";
        this.hoverImagePath = "/gui/shop/damageHover.png";
        this.pressedImagePath = "/gui/shop/damagePressed.png";

        setDefaultValues(shop.shopPositionX + ts * 2 + ts / 2, shop.shopPositionY + ts * 5, 0);
        hitbox = setHitbox(x, y, ts * 2, ts * 2, false);
    }

    @Override
    protected void mouseReleasedEvent() {
        if (money.moneyAmount >= cost) {
            System.out.println("Damage+");
            // money.moneyAmount -= cost;
        }
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {
        // shop.drawShopItem(g2, image, x, y, "Damage+", "1000$", "", hitbox);
        shop.drawShopItem(g2, image, x, y, "Damage+", "Not yet", "implemented", hitbox);
    }
}
