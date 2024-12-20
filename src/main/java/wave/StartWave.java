package wave;

import entity.EnemyA;
import entity.EnemyB;
import main.GamePanel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class StartWave<T> {

    private <T> void startSpawner(List<T> enemyList, int initialCount, Supplier<T> enemySupplier, int minInterval, int maxInterval, int initialDelay) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        final int[] enemyCount = {initialCount};
        int rnd = (int) (Math.random() * (maxInterval - minInterval + 1)) + minInterval;

        executor.scheduleAtFixedRate(() -> {
            if (enemyCount[0] > 0) {
                spawnEnemy(enemyList, enemySupplier);
                enemyCount[0]--;
            } else {
                executor.shutdown();
            }
        }, initialDelay, rnd, TimeUnit.MILLISECONDS);
    }

    private <T> void spawnEnemy(List<T> enemyList, Supplier<T> enemySupplier) {
        T enemy = enemySupplier.get();
        enemyList.add(enemy);
    }

    public <T> void startEnemySpawner(List<T> enemyList, Supplier<T> enemySupplier, int enemyCount, int minInterval, int maxInterval, int initialDelay) {
        startSpawner(enemyList, enemyCount, enemySupplier, minInterval, maxInterval, initialDelay);
    }
}