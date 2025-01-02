package entity.gui;

import entity.Entity;
import main.ClickChecker;
import main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class BasicButton extends Entity implements MouseMotionListener, MouseListener {
    ClickChecker clickChecker = new ClickChecker();

    protected int ts = gamePanel.tileSize;
    protected int sh = gamePanel.screenHeight;

    protected String imagePath = "";
    protected String hoverImagePath = "";
    protected String pressedImagePath = "";

    public BasicButton(GamePanel gamePanel) {
        super(gamePanel);

        setDefaultValues(0, 0, 0);
        getImage(imagePath);

        gamePanel.addMouseMotionListener(this);
        gamePanel.addMouseListener(this);
    }

    protected abstract void mouseReleasedEvent();

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
