package entity.enemy;

import main.GamePanel;

public class EnemyA extends Enemy {
    public static int reward = 10;

    public EnemyA(GamePanel gamePanel) {
        super(gamePanel);
        this.imagePath = "/enemy/EnemyA.png";
        this.speed = 2;

        getImage(imagePath);
    }
}
