package entity.gui;

import entity.Entity;
import main.ClickChecker;
import main.GamePanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class StartButton extends Entity implements MouseMotionListener, MouseListener {
    private Boolean enemiesCleared;
    ClickChecker clickChecker = new ClickChecker();
    public Boolean startSignal = false;
    public int wave = 0;
    int ts = gamePanel.tileSize;
    int sh = gamePanel.screenHeight;

    public StartButton(GamePanel gamePanel) {
        super(gamePanel);

        setDefaultValues(0, sh - ts, 0);
        getImage("/gui/startButton.png");
        hitbox = drawHitbox(x, y, ts * 2, ts, false);

        gamePanel.addMouseMotionListener(this);
        gamePanel.addMouseListener(this);
    }

    public void update() {
        enemiesCleared = gamePanel.enemiesCleared;
    }

    public void draw(Graphics2D g2) {
        if (enemiesCleared) {
            g2.drawImage(image, x, y, ts * 2, ts, null);
            hitbox = drawHitbox(x, y, ts * 2, ts, false);
        } else {
            g2.drawImage(image, x, y + 100, ts * 2, ts, null);
            hitbox = drawHitbox(x, y + 100, ts * 2, ts, false);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ClickChecker clickChecker = new ClickChecker();

        if (!clickChecker.checkClickedOn(this, e.getPoint())) {
            getImage("/gui/startButton.png");
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (clickChecker.checkClickedOn(this, e.getPoint())) {
            getImage("/gui/startButtonHover.png");
        } else {
            getImage("/gui/startButton.png");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (clickChecker.checkClickedOn(this, e.getPoint())) {
            getImage("/gui/startButtonPressed.png");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (clickChecker.checkClickedOn(this, e.getPoint())) {
            getImage("/gui/startButtonHover.png");
            wave++;
            startSignal = true;
            System.out.println("START WAVE");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
