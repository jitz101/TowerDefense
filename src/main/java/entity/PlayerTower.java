package entity;

import entity.projectile.PlayerProjectile;
import main.CollisionChecker;
import main.GamePanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlayerTower extends Entity implements MouseMotionListener, MouseListener {
    public List<PlayerProjectile> projectiles = new ArrayList<>();

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

        Iterator<PlayerProjectile> iterator = projectiles.iterator();

        while (iterator.hasNext()) {
            PlayerProjectile projectile = iterator.next();

            if (projectile.xD > gamePanel.screenWidth || projectile.xD < 0 || projectile.yD > gamePanel.screenHeight || projectile.yD < 0) {
                iterator.remove();
            }
        }
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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        int centerX = x + gamePanel.tileSize / 2;
        int centerY = y + gamePanel.tileSize / 2;

        angle = Math.atan2(mouseY - centerY, mouseX - centerX);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            // Berechne den Winkel zum Mauszeiger
            int mouseX = e.getX();
            int mouseY = e.getY();
            int centerX = x + gamePanel.tileSize / 2;
            int centerY = y + gamePanel.tileSize / 2;

            double projectileAngle = Math.atan2(mouseY - centerY, mouseX - centerX);

            // Erstelle ein neues Projektil mit der berechneten Richtung
            projectiles.add(new PlayerProjectile(gamePanel, centerX, centerY, projectileAngle));
        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
