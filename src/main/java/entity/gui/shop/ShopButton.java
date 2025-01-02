package entity.gui.shop;

import entity.Entity;
import main.ClickChecker;
import main.GamePanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ShopButton extends Entity implements MouseMotionListener, MouseListener {
    ClickChecker clickChecker = new ClickChecker();
    int ts = gamePanel.tileSize;
    int sh = gamePanel.screenHeight;
    int sw = gamePanel.screenWidth;
    public static Boolean shopSignal = false;

    String imagePath = "/gui/shop/shopButton.png";
    String hoverImagePath = "/gui/shop/shopButtonHover.png";
    String pressedImagePath = "/gui/shop/shopButtonPressed.png";

    public ShopButton(GamePanel gamePanel) {
        super(gamePanel);

        setDefaultValues(sw - ts * 2, sh - ts, 0);
        getImage(imagePath);
        hitbox = setHitbox(x, y, ts * 2, ts, false);

        gamePanel.addMouseMotionListener(this);
        gamePanel.addMouseListener(this);
    }

    private void mouseReleasedEvent() {
        shopSignal = !shopSignal;
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, ts * 2, ts, null);
        hitbox = setHitbox(x, y, ts * 2, ts, false);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!clickChecker.checkClickedOn(this, e.getPoint())) {
            getImage(imagePath);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (clickChecker.checkClickedOn(this, e.getPoint())) {
            getImage(hoverImagePath);
        } else {
            getImage(imagePath);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (clickChecker.checkClickedOn(this, e.getPoint())) {
            getImage(pressedImagePath);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (clickChecker.checkClickedOn(this, e.getPoint())) {
            getImage(hoverImagePath);
            mouseReleasedEvent();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
