package main;

import entity.*;
import entity.projectile.PlayerProjectile;
import wave.StartWave;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16;
    public final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    public final int centerTileX = screenWidth / 2 - tileSize / 2;
    public final int centerTileY = screenHeight / 2 - tileSize / 2;
    public final int centerWidth = screenWidth / 2;
    public final int centerHeight = screenHeight / 2;

    int FPS = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Base base = new Base(this);
    PlayerTower playerTower = new PlayerTower(this);
    List<EnemyA> enemyAList = new ArrayList<>();
    List<EnemyB> enemyBList = new ArrayList<>();

    StartWave waveA = new StartWave();

    CollisionChecker cc = new CollisionChecker();


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        waveA.startEnemySpawner(enemyAList, () -> new EnemyA(this), 10, 1000, 3000, 0);
        waveA.startEnemySpawner(enemyBList, () -> new EnemyB(this), 10, 1000, 3000, 5000);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); //calls run()
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        base.update();
        playerTower.update();

        for (EnemyA enemy : enemyAList) {
            enemy.update();
        }
        for (EnemyB enemy : enemyBList) {
            enemy.update();
        }

        cc.checkEntityListHitEntity(enemyAList, base);
        cc.checkEntityListHitEntity(enemyBList, base);

        cc.checkEntityListHitEntityList(enemyAList, playerTower.projectiles);
        cc.checkEntityListHitEntityList(enemyBList, playerTower.projectiles);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        base.draw(g2);
        playerTower.draw(g2);

        for (EnemyA enemy : enemyAList) {
            enemy.draw(g2);
        }
        for (EnemyB enemy : enemyBList) {
            enemy.draw(g2);
        }


        g2.dispose();
    }
}
