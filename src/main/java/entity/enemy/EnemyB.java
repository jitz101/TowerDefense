package entity.enemy;

import main.GamePanel;

public class EnemyB extends Enemy {
    public static int reward = 20;

    public EnemyB(GamePanel gamePanel) {
        super(gamePanel);
        this.imagePath = "/enemy/EnemyB.png";
        this.speed = 5;

        getImage(imagePath);
    }
}
