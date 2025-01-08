package main;

import entity.*;
import entity.enemy.EnemyA;
import entity.enemy.EnemyB;
import entity.gui.Money;
import entity.gui.StartButton;
import entity.gui.Wave;
import entity.gui.shop.*;
import wave.StartWave;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    public final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile;
    public final int maxScreenCol = 24; // 16
    public final int maxScreenRow = 18; // 12
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    public final int centerTileX = screenWidth / 2 - tileSize / 2;
    public final int centerTileY = screenHeight / 2 - tileSize / 2;
    public final int centerWidth = screenWidth / 2;
    public final int centerHeight = screenHeight / 2;

    int FPS = 60;
    public static int enemyCount = 0;

    Boolean firstDraw = true;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Base base = new Base(this);
    PlayerTower playerTower = new PlayerTower(this);
    CopyOnWriteArrayList<EnemyA> enemyAList = new CopyOnWriteArrayList<>();
    CopyOnWriteArrayList<EnemyB> enemyBList = new CopyOnWriteArrayList<>();
    public StartWave startWave = new StartWave();
    CollisionChecker cc = new CollisionChecker();

    StartButton startButton = new StartButton(this);
    ShopButton shopButton = new ShopButton(this);
    Money money = new Money(this);
    Wave wave = new Wave(this);
    Shop shop = new Shop(this);
    RateOfFireButton rateOfFireButton = new RateOfFireButton(this, money, playerTower);
    DamageButton damageButton = new DamageButton(this, money);
    BulletSpeedButton bulletSpeedButton = new BulletSpeedButton(this, money, playerTower);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); //calls run()
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
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
                System.err.println("Error in run(): " + e.getMessage());
            }
        }
    }

    public void update() {
        base.update();
        playerTower.update();

        int enemyACount;
        int enemyBCount;
        startButton.update();
        if (startButton.startSignal) {
            switch (Wave.currentWave) {
                case 1:
                    enemyACount = 10;
                    enemyCount += enemyACount;

                    startWave.startEnemySpawner(enemyAList, () -> new EnemyA(this), enemyACount, 1000, 1000, 0);
                    startButton.startSignal = false;
                    break;
                case 2:
                    enemyACount = 15;
                    enemyBCount = 1;
                    enemyCount += enemyACount + enemyBCount;

                    startWave.startEnemySpawner(enemyAList, () -> new EnemyA(this), enemyACount, 500, 1200, 0);
                    startWave.startEnemySpawner(enemyBList, () -> new EnemyB(this), enemyBCount, 1, 1, 10000);
                    startButton.startSignal = false;
                    break;
                case 3:
                    enemyBCount = 10;
                    enemyCount += enemyBCount;

                    startWave.startEnemySpawner(enemyBList, () -> new EnemyB(this), enemyBCount, 1000, 2000, 1000);
                    startButton.startSignal = false;
                    break;
                case 4:
                    enemyACount = 10;
                    enemyBCount = 5;
                    enemyCount += enemyACount + enemyBCount;

                    startWave.startEnemySpawner(enemyAList, () -> new EnemyA(this), enemyACount, 500, 1200, 0);
                    startWave.startEnemySpawner(enemyBList, () -> new EnemyB(this), enemyBCount, 1000, 5000, 3000);
                    startButton.startSignal = false;
                    break;
                case 5:
                    enemyACount = 30;
                    enemyCount += enemyACount;

                    startWave.startEnemySpawner(enemyAList, () -> new EnemyA(this), enemyACount, 200, 800, 1000);
                    startButton.startSignal = false;
                    break;
                case 6:
                    enemyACount = 20;
                    enemyBCount = 10;
                    enemyCount += enemyACount + enemyBCount;

                    startWave.startEnemySpawner(enemyAList, () -> new EnemyA(this), enemyACount, 500, 1200, 0);
                    startWave.startEnemySpawner(enemyBList, () -> new EnemyB(this), enemyBCount, 1000, 3000, 5000);
                    startButton.startSignal = false;
                    break;
                case 7:
                    enemyACount = 10;
                    enemyBCount = 20;
                    enemyCount += enemyACount + enemyBCount;

                    startWave.startEnemySpawner(enemyAList, () -> new EnemyA(this), enemyACount, 500, 1200, 5000);
                    startWave.startEnemySpawner(enemyBList, () -> new EnemyB(this), enemyBCount, 1000, 1000, 0);
                    startButton.startSignal = false;
                    break;
                case 8:
                    enemyACount = 10;
                    enemyCount += enemyACount;

                    startWave.startEnemySpawner(enemyAList, () -> new EnemyA(this), enemyACount, 1, 1, 0);
                    startButton.startSignal = false;
                    break;
                case 9:
                    enemyBCount = 5;
                    enemyCount += enemyBCount;

                    startWave.startEnemySpawner(enemyBList, () -> new EnemyB(this), enemyBCount, 1, 1, 0);
                    startButton.startSignal = false;
                    break;
                case 10:
                    enemyACount = 10;
                    enemyBCount = 5;
                    enemyCount += enemyACount + enemyBCount;

                    startWave.startEnemySpawner(enemyAList, () -> new EnemyA(this), enemyACount, 1, 1, 0);
                    startWave.startEnemySpawner(enemyBList, () -> new EnemyB(this), enemyBCount, 1, 1, 0);
                    startButton.startSignal = false;
                    break;
            }
        }

        for (EnemyA enemy : enemyAList) {enemy.update();}
        for (EnemyB enemy : enemyBList) {enemy.update();}

        cc.checkEntityListHitEntity(enemyAList, base);
        cc.checkEntityListHitEntity(enemyBList, base);

        cc.checkEntityListHitEntityList(enemyAList, playerTower.projectiles, EnemyA.reward, money);
        cc.checkEntityListHitEntityList(enemyBList, playerTower.projectiles, EnemyB.reward, money);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        if (firstDraw) {
            shop.draw(g2);
            rateOfFireButton.draw(g2);
            damageButton.draw(g2);
            bulletSpeedButton.draw(g2);
            firstDraw = false;
        }

        base.draw(g2);
        playerTower.draw(g2);

        for (EnemyA enemy : enemyAList) {
            enemy.draw(g2);
        }
        for (EnemyB enemy : enemyBList) {
            enemy.draw(g2);
        }

        startButton.draw(g2);
        shopButton.draw(g2);
        money.draw(g2);
        wave.draw(g2);

        if (ShopButton.shopSignal) {
            shop.draw(g2);
            rateOfFireButton.draw(g2);
            damageButton.draw(g2);
            bulletSpeedButton.draw(g2);
            firstDraw = true;
        }

        g2.dispose();
    }
}
