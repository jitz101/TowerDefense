package entity.gui;

import main.GamePanel;

import java.awt.*;

public class StartButton extends BasicButton {
    private Boolean enemiesCleared;
    public Boolean startSignal = false;
    public int wave = 0;

    public StartButton(GamePanel gamePanel) {
        super(gamePanel);

        this.imagePath = "/gui/startButton.png";
        this.hoverImagePath = "/gui/startButtonHover.png";
        this.pressedImagePath = "/gui/startButtonPressed.png";

        setDefaultValues(0, sh - ts, 0);
        hitbox = setHitbox(x, y, ts * 2, ts, false);
    }

    @Override
    protected void mouseReleasedEvent() {
        wave++;
        startSignal = true;
    }

    public void update() {
        enemiesCleared = gamePanel.enemiesCleared;
    }

    public void draw(Graphics2D g2) {
        if (enemiesCleared) {
            g2.drawImage(image, x, y, ts * 2, ts, null);
            hitbox = setHitbox(x, y, ts * 2, ts, false);
        } else {
            g2.drawImage(image, x, y + 100, ts * 2, ts, null);
            hitbox = setHitbox(x, y + 100, ts * 2, ts, false);
        }
    }
}
