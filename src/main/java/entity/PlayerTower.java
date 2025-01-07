package entity;

import entity.projectile.PlayerProjectile;
import main.GamePanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlayerTower extends Entity implements MouseMotionListener, MouseListener {
    public CopyOnWriteArrayList<PlayerProjectile> projectiles = new CopyOnWriteArrayList<>();
    volatile private boolean mouseDown = false;
    public int projectileSpeed = 5;
    public int reloadTime = 1000;

    public PlayerTower(GamePanel gamePanel) {
        super(gamePanel);

        setDefaultValues(gamePanel.centerTileX, gamePanel.centerTileY - gamePanel.tileSize, 0);
        getImage("/playerTower/playerTower.png");

        gamePanel.addMouseMotionListener(this);
        gamePanel.addMouseListener(this);
    }

    public void update() {
        for (PlayerProjectile projectile : projectiles) {
            projectile.update();
        }

        projectiles.removeIf(
        projectile ->
            projectile.xD > gamePanel.screenWidth ||
            projectile.xD < 0 ||
            projectile.yD > gamePanel.screenHeight ||
            projectile.yD < 0
        );
    }

    public void draw(Graphics2D g2) {
        int centerX = x + gamePanel.tileSize / 2;
        int centerY = y + gamePanel.tileSize / 2;

        var originalTransform = g2.getTransform();

        g2.translate(centerX, centerY);
        g2.rotate(angle);
        g2.translate(-gamePanel.tileSize / 2, -gamePanel.tileSize / 2);

        g2.drawImage(image, 0, 0, gamePanel.tileSize, gamePanel.tileSize, null);

        g2.setTransform(originalTransform);

        for (PlayerProjectile projectile : projectiles) {
            projectile.draw(g2);
        }
    }

    private void turnTower(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        int centerX = x + gamePanel.tileSize / 2;
        int centerY = y + gamePanel.tileSize / 2;

        angle = Math.atan2(mouseY - centerY, mouseX - centerX);
    }

    volatile private boolean isRunning = false;

    private synchronized boolean checkAndMark() {
        if (isRunning) {
            return false;
        }

        isRunning = true;
        return true;
    }

    private void shootingThread() {
        if (checkAndMark()) {
            new Thread(() -> {
                do {
                    try {
                        Point mousePosition = gamePanel.getMousePosition();
                        int mouseX = (int) mousePosition.getX();
                        int mouseY = (int) mousePosition.getY();
                        int centerX = x + gamePanel.tileSize / 2;
                        int centerY = y + gamePanel.tileSize / 2;

                        double projectileAngle = Math.atan2(mouseY - centerY, mouseX - centerX);

                        projectiles.add(new PlayerProjectile(gamePanel, centerX, centerY, projectileAngle, projectileSpeed));
                    } catch (NullPointerException ignored) {}

                    try {
                        Thread.sleep(reloadTime);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } while (mouseDown);
                isRunning = false;
            }).start();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        turnTower(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        turnTower(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseDown = true;
            shootingThread();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseDown = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
